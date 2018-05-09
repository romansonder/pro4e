/* ts3a27518e Multiplexer USB/Audio nach SD */

#include <stdbool.h>
#include <stdint.h>

#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "dojo.h"


#include "ts3a27518e.h"

/******************************************************************************/
/* edit IO-Ports as you need them */
static const uint32_t en_pin = MUX_EN_PIN;
static const uint32_t in_pin = MUX_IN_PIN;

/******************************************************************************/
extern void ts3a27518e_init(void){
	nrf_gpio_cfg_output(en_pin);
	nrf_gpio_cfg_output(in_pin);
}

extern void ts3a27518e_usb_mode(void){
	nrf_gpio_pin_set(en_pin);
	nrf_delay_ms(100);
	nrf_gpio_pin_clear(in_pin);
	nrf_gpio_pin_clear(en_pin);
}

extern void ts3a27518e_audio_mode(void){
	nrf_gpio_pin_set(en_pin);
	nrf_delay_ms(100);
	nrf_gpio_pin_set(in_pin);
	nrf_gpio_pin_clear(en_pin);
}
/******************************************************************************/
