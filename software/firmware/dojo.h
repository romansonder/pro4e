#ifndef DOJO_H
#define DOJO_H

#ifdef __cplusplus
extern "C" {
#endif

#include "nrf_gpio.h"

// LEDs definitions for PCA10040
#define LEDS_NUMBER    2

#define LED_1          26
#define LED_2          27


#define LEDS_ACTIVE_STATE 0

#define LEDS_INV_MASK  LEDS_MASK

#define LEDS_LIST { LED_1, LED_2}

#define BSP_LED_0      LED_1
#define BSP_LED_1      LED_2


#define BUTTONS_NUMBER 4

#define BUTTON_PLAY    31
#define BUTTON_INC_VOL 29
#define BUTTON_DEC_VOL 30
#define BUTTON_LIKE    28
#define BUTTON_PULL    NRF_GPIO_PIN_PULLUP

#define BUTTONS_ACTIVE_STATE 0

#define BUTTONS_LIST { BUTTON_PLAY, BUTTON_INC_VOL, BUTTON_DEC_VOL,BUTTON_LIKE }

#define BSP_BUTTON_2   BUTTON_PLAY
#define BSP_BUTTON_4   BUTTON_INC_VOL
#define BSP_BUTTON_5   BUTTON_DEC_VOL
#define BSP_BUTTON_6   BUTTON_LIKE

#define AUDIO_BUSY_PIN		5
#define AUDIO_PLAY_PIN		4
#define AUDIO_DCLK_PIN		7
#define AUDIO_RESET_PIN		11
#define AUDIO_DIN_PIN		6
#define AUDIO_STANDBY_PIN	3
#define AUDIO_PREV_PIN		8
#define AUDIO_NEXT_PIN		12

#define MUX_IN_PIN		13
#define MUX_EN_PIN		14

#define BATTERY_PWR_PIN 	2

#define VIBRO_PIN		18

#define UART_TX			15
#define UART_RX			16

// Low frequency clock source to be used by the SoftDevice
#define NRF_CLOCK_LFCLKSRC      {.source       = NRF_CLOCK_LF_SRC_XTAL,      \
                                 .rc_ctiv      = 0,                          \
                                 .rc_temp_ctiv = 0,                          \
                                 .accuracy     = NRF_CLOCK_LF_ACCURACY_20_PPM}

#ifdef __cplusplus
}
#endif

#endif // DOJO_H
