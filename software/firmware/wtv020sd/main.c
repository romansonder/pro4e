/* Testfile */

#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "boards.h"

#include "wtv020sd.h"

#define TEST1_LED 18
int main (void)
{
	wtv020sd_init();
	while(1){
	wtv020sd_play_audio(0);
	nrf_delay_ms(5000);

	wtv020sd_play_pause();
	nrf_delay_ms(5000);
	wtv020sd_play_pause();
	nrf_delay_ms(5000);

	wtv020sd_play_audio(2);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	nrf_delay_ms(5000);
	wtv020sd_red_vol();
	wtv020sd_red_vol();
	wtv020sd_red_vol();
	nrf_delay_ms(5000);

	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	wtv020sd_inc_vol();
	wtv020sd_inc_vol();
	wtv020sd_inc_vol();
	nrf_delay_ms(5000);
	}
}
