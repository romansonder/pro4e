/* dojo periphery */

#ifndef PERIPHERY_H
#define PERIPHERY_H

#include <stdbool.h>
#include <stdint.h>

#include "nrf_gpio.h"
#include "nrf_drv_gpiote.h"

extern void periphery_init(void);

extern void periphery_button_action(void);
extern void in_pin_handler(nrf_drv_gpiote_pin_t pin, nrf_gpiote_polarity_t action);


#endif /* PERIPHERY_H */
