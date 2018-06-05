#include <stdbool.h>

#include "nrf.h"
#include "nrf_drv_gpiote.h"
#include "app_error.h"

#include "dojo.h"

#include "periphery.h"

/* Noch ausstehend
 * - in- decvolume3
 * - aufräumen
 * */

int main(void)
{
	periphery_init();

	while (true)
	{
		periphery_button_action();
		// Do nothing.
	}
}


/** @} */
