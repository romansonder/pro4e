/* ts4871 Audioamplifier */

#include <stdbool.h>
#include <stdint.h>

#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "dojo.h"


#include "ts4871.h"


/******************************************************************************/
/* edit IO-Ports as you need them */
static const uint32_t standby_pin = AUDIO_STANDBY_PIN;

/******************************************************************************/
extern void ts4871_init(void){
	nrf_gpio_cfg_output(standby_pin);
}

extern void ts4871_ein(void){
	nrf_gpio_pin_clear(AUDIO_STANDBY_PIN);
}

extern void ts4871_aus(void){
	nrf_gpio_pin_set(AUDIO_STANDBY_PIN);
}
/******************************************************************************/
