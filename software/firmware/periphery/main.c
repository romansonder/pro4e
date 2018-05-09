#include <stdbool.h>

#include "nrf.h"
#include "nrf_drv_gpiote.h"
#include "app_error.h"

#include "dojo.h"
#include "nrf_delay.h"

#include "periphery.h"

int main(void)
{
	periphery_init();

	while (true)
	{
		nrf_gpio_pin_clear(LED_1);
		nrf_delay_ms(500);
		nrf_gpio_pin_set(LED_1);
		nrf_delay_ms(500);
	}
}


/** @} */
