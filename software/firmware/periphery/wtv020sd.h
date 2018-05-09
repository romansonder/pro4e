/*  wtv020sd  Audiointerface */

#ifndef WTV020SD_H
#define WTV020SD_H


#include <stdbool.h>
#include <stdint.h>

#include "nrf_delay.h"
#include "nrf_gpio.h"
#include "dojo.h"



#define PLAY_PAUSE 	0xFFFE
#define STOP		0xFFFF
#define VOL_MIN		0xFFF0
#define VOL_MAX		0xFFF7


extern void wtv020sd_init(void);
extern void wtv020sd_play_pause(void);
extern void wtv020sd_stop(void);
extern void wtv020sd_inc_vol(void);
extern void wtv020sd_dec_vol(void);
extern void wtv020sd_reset(void);
extern void wtv020sd_play_audio(uint16_t audio_name);
extern uint8_t wtv020sd_status(void);


#endif /* WTV020SD_H */
