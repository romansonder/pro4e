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
	}
}