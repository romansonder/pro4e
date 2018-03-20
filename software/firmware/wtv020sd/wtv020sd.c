/* lizenz */

/* wtv020sd Audiointerface */

#include <stdbool.h>
#include <stdint.h>

#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "boards.h"

#include "wtv020sd.h"

/******************************************************************************/
static void wtv020sd_send(uint16_t command);

/******************************************************************************/
/* edit IO-Ports as you need them */
static const uint32_t data_pin = 12;
static const uint32_t reset_pin = 15;
static const uint32_t clk_pin = 13;
static const uint32_t busy_pin = 14;

static uint16_t current_vol = VOL_MAX;

/******************************************************************************/
/* set-up inputs and outputs for communication to audio interface */
extern void wtv020sd_init(void){
	/* define outputs */
	nrf_gpio_cfg_output(data_pin);
	nrf_gpio_cfg_output(clk_pin);
	nrf_gpio_cfg(reset_pin, NRF_GPIO_PIN_DIR_OUTPUT, NRF_GPIO_PIN_INPUT_DISCONNECT, NRF_GPIO_PIN_NOPULL, NRF_GPIO_PIN_S0D1, NRF_GPIO_PIN_NOSENSE);

	/* set pins to no data */
	nrf_gpio_pin_set(data_pin);
	nrf_gpio_pin_set(clk_pin);
	nrf_gpio_pin_set(reset_pin);

	/* define inputs */
	nrf_gpio_cfg_input(busy_pin, NRF_GPIO_PIN_PULLDOWN);

	/* define start volume */
	wtv020sd_send(current_vol);
	wtv020sd_reset();
}

extern void wtv020sd_play_pause(void){
	wtv020sd_send(PLAY_PAUSE);
}

extern void wtv020sd_stop(void){
	wtv020sd_send(STOP);
}

extern void wtv020sd_inc_vol(void){
	if(current_vol < VOL_MAX){
		current_vol = current_vol + 1;
		wtv020sd_send(current_vol);
	}
}

extern void wtv020sd_dec_vol(void){
	if(current_vol > VOL_MIN){
		current_vol = current_vol - 1;
		wtv020sd_send(current_vol);
	}
}

extern void wtv020sd_reset(void){
	nrf_gpio_pin_set(clk_pin);
	nrf_gpio_pin_set(reset_pin);

	/* trigger reset pin */
	nrf_gpio_pin_clear(reset_pin);
	nrf_delay_ms(5);
	nrf_gpio_pin_set(reset_pin);

	nrf_delay_ms(500);
}

extern void wtv020sd_play_audio(uint16_t audio_name){
	wtv020sd_send(audio_name);
}

extern uint8_t wtv020sd_status(void){
	return 1;
}

/******************************************************************************/
static void wtv020sd_send(uint16_t command){
	/* clock init */
	nrf_gpio_pin_clear(clk_pin);
	nrf_delay_ms(2);

	for(int i = 15; i > -1; i--){
		/* first half low clock pulse */
		nrf_gpio_pin_clear(clk_pin);
		nrf_delay_us(50);

		uint16_t mask = 0x0001;
		if(mask & (~mask | (command >> i))){
			nrf_gpio_pin_set(data_pin);
		}else{
			nrf_gpio_pin_clear(data_pin);
		}

		/* second half low clock pulse */
		nrf_delay_us(50);

		/* full high clock pulse */
		nrf_gpio_pin_set(clk_pin);
		nrf_delay_us(100);

	}

	nrf_gpio_pin_set(data_pin);
	nrf_gpio_pin_set(clk_pin);

	/* wait for busy activity from interface*/
	nrf_delay_ms(20);
}
