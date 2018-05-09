/* battery status */

#ifndef BATTERY_H
#define BATTERY_H

#include <stdbool.h>
#include <stdint.h>

extern void battery_status_init(void);
extern uint16_t battery_status(void);


#endif /* BATTERY_H */
