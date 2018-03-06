package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class TimerThread extends Thread {
	private boolean isRunning;

	private JLabel tempLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss");

	public TimerThread(JLabel dateLabel, JLabel timeLabel) {
		this.dateLabel = dateLabel;
		this.timeLabel = timeLabel;
		setRunning(true);
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setTemperatur(int temperature) {
		this.tempLabel.setText(Integer.toString(temperature) + " °C");
	}
}