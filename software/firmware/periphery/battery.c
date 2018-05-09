/* battery status */

#include <stdbool.h>
#include <stdint.h>

#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "dojo.h"


#include "battery.h"


/******************************************************************************/
/* edit IO-Ports as you need them */
static const uint32_t battery_pin = BATTERY_PWR_PIN;

/******************************************************************************/
extern void battery_status_init(void){
	nrf_gpio_cfg_input(battery_pin);
}
extern uint16_t battery_status(void){
	nrf
}
/******************************************************************************/
