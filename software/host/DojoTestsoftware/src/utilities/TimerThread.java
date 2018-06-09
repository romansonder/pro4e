/*******************************************************************************
 * Copyright (C) 2018  FHNW Pro4E FS18 Team 3
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import model.StatusType;
import userinterface.StatusBar;

public class TimerThread extends Thread {
	private boolean isRunning;
	private boolean isTransmittingRunning;
	private boolean isPreferencesTransmittingRunning;
	private boolean isEvaluationRunning;
	private int counter = 0;

	private JLabel dateLabel;
	private JLabel timeLabel;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss");

	public TimerThread(JLabel dateLabel, JLabel timeLabel) {
		this.dateLabel = dateLabel;
		this.timeLabel = timeLabel;
		this.counter = 0;
		setRunning(true);
		setTransmittingRunning(false);
	}

	@Override
	public void run() {
		while (isRunning) {
			Calendar currentCalendar = Calendar.getInstance();
			Date currentTime = currentCalendar.getTime();
			dateLabel.setText(dateFormat.format(currentTime));
			timeLabel.setText(timeFormat.format(currentTime));

			try {
				TimeUnit.SECONDS.sleep(1);

				if (isTransmittingRunning) {
					switch (counter) {
					case 0:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, " ");
						counter++;
						break;
					case 1:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, ".");
						counter++;
						break;
					case 2:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, ". .");
						counter++;
						break;
					case 3:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, ". . .");
						counter++;
						break;
					case 4:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, ". . . .");
						counter++;
						break;
					case 5:
						StatusBar.setStatus(StatusType.TRANSMITTINGDATA, ". . . . .");
						counter = 0;
						break;
					default:
						break;
					}
				}

				if (isPreferencesTransmittingRunning) {
					switch (counter) {
					case 0:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, " ");
						counter++;
						break;
					case 1:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, ".");
						counter++;
						break;
					case 2:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, ". .");
						counter++;
						break;
					case 3:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, ". . .");
						counter++;
						break;
					case 4:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, ". . . .");
						counter++;
						break;
					case 5:
						StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, ". . . . .");
						counter = 0;
						break;
					default:
						break;
					}
				}

				if (isEvaluationRunning) {
					switch (counter) {
					case 0:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, " ");
						counter++;
						break;
					case 1:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, ".");
						counter++;
						break;
					case 2:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, ". .");
						counter++;
						break;
					case 3:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, ". . .");
						counter++;
						break;
					case 4:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, ". . . .");
						counter++;
						break;
					case 5:
						StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, ". . . . .");
						counter = 0;
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setTransmittingRunning(boolean isTransmittingRunning) {
		this.isTransmittingRunning = isTransmittingRunning;

		if (isTransmittingRunning == false) {
			counter = 0;
		}
	}

	public void setPreferencesTransmittingRunning(boolean isPreferencesTransmittingRunning) {
		this.isPreferencesTransmittingRunning = isPreferencesTransmittingRunning;

		if (isPreferencesTransmittingRunning == false) {
			counter = 0;
		}
	}

	public void setEvaluationRunning(boolean isEvaluationRunning) {
		this.isEvaluationRunning = isEvaluationRunning;

		if (isEvaluationRunning == false) {
			counter = 0;
		}
	}
}
