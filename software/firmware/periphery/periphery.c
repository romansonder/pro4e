/* dojo periphery */

#include <stdbool.h>
#include <stdint.h>

#include "nrf.h"
#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "nrf_drv_gpiote.h"
#include "app_error.h"
#include "dojo.h"


#include "wtv020sd.h"
#include "ts4871.h"
#include "ts3a27518e.h"
#include "periphery.h"

static uint8_t activ_audio_file = 0;
static nrf_drv_gpiote_pin_t butten_activ_pin = 0;

uint16_t swap = 0;

/******************************************************************************/
/* edit IO-Ports as you need them */
static nrf_drv_gpiote_pin_t play_button = BUTTON_PLAY;
static nrf_drv_gpiote_pin_t inc_button = BUTTON_INC_VOL;
static nrf_drv_gpiote_pin_t dec_button = BUTTON_DEC_VOL;
static nrf_drv_gpiote_pin_t like_button = BUTTON_LIKE;

/******************************************************************************/

extern void in_pin_handler(nrf_drv_gpiote_pin_t pin, nrf_gpiote_polarity_t action){
	if(pin == play_button){
		butten_activ_pin = play_button;
	}else if(pin == inc_button){
		butten_activ_pin = inc_button;
	}else if(pin == dec_button){
		butten_activ_pin = dec_button;
	}else if(pin == like_button){
		butten_activ_pin = like_button;
	}else{
		butten_activ_pin = 0;
	}
}

extern void periphery_init(void){
	/* Enable audioampliphier */
	ts4871_init();
	ts4871_ein();

	/* Enable multiplexer */
	ts3a27518e_init();
	ts3a27518e_audio_mode();

	/* Enable audiointerface*/
	wtv020sd_init();

	ret_code_t err_code;

	err_code = nrf_drv_gpiote_init();
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_out_config_t out_config = GPIOTE_CONFIG_OUT_SIMPLE(false);

	err_code = nrf_drv_gpiote_out_init(LED_1, &out_config);
	APP_ERROR_CHECK(err_code);

	err_code = nrf_drv_gpiote_out_init(LED_2, &out_config);
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_in_config_t in_config = GPIOTE_CONFIG_IN_SENSE_HITOLO(true);
	in_config.pull = NRF_GPIO_PIN_PULLUP;


	err_code = nrf_drv_gpiote_in_init(play_button, &in_config, in_pin_handler);
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_in_event_enable(play_button, true);

	err_code = nrf_drv_gpiote_in_init(inc_button, &in_config, in_pin_handler);
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_in_event_enable(inc_button, true);

	err_code = nrf_drv_gpiote_in_init(dec_button, &in_config, in_pin_handler);
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_in_event_enable(dec_button, true);

	err_code = nrf_drv_gpiote_in_init(like_button, &in_config, in_pin_handler);
	APP_ERROR_CHECK(err_code);

	nrf_drv_gpiote_in_event_enable(like_button, true);

}

extern void periphery_button_action(void){
	// /* USB MODUS */
	// if(butten_activ_pin == power_button){
		// nrf_gpio_pin_clear(LED_1);
		// nrf_gpio_pin_set(LED_2);
		// /* Swap SD */
		// ts3a27518e_usb_mode();

		// butten_activ_pin = 0;

	// /* AUDIO MODUS */
	// }else if(butten_activ_pin == vibro_button){
		// nrf_gpio_pin_clear(LED_2);
		// nrf_gpio_pin_set(LED_1);
		// /* Swap SD */
		// ts3a27518e_audio_mode();

		// wtv020sd_reset();
		// wtv020sd_play_audio(activ_audio_file);

		// butten_activ_pin = 0;
	if(butten_activ_pin == play_button){
		nrf_gpio_pin_toggle(LED_1);
		nrf_delay_ms(500);
		nrf_gpio_pin_toggle(LED_1);

		wtv020sd_play_pause();
		butten_activ_pin = 0;
	}else if(butten_activ_pin == inc_button){
		nrf_gpio_pin_toggle(LED_1);
		nrf_delay_ms(500);
		nrf_gpio_pin_toggle(LED_1);

		wtv020sd_inc_vol();
		butten_activ_pin = 0;
	}else if(butten_activ_pin == dec_button){
		nrf_gpio_pin_toggle(LED_1);
		nrf_delay_ms(500);
		nrf_gpio_pin_toggle(LED_1);

		wtv020sd_dec_vol();
		butten_activ_pin = 0;
	}else if(butten_activ_pin == like_button){
		nrf_gpio_pin_toggle(LED_1);
		nrf_delay_ms(500);
		nrf_gpio_pin_toggle(LED_1);

		if(swap){
			/* USB MODUS */
			nrf_gpio_pin_clear(LED_1);
			nrf_gpio_pin_set(LED_2);
			/* Swap SD */
			ts3a27518e_usb_mode();
		}

		butten_activ_pin = 0;
	}else{
		butten_activ_pin = 0;
	}
}
/******************************************************************************/
