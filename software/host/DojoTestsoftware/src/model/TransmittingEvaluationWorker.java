package model;

import java.util.TimerTask;

import javax.swing.SwingWorker;

import protocol.JavaBleCommunication;
import userinterface.StatusBar;

public class TransmittingEvaluationWorker extends SwingWorker<Object, Object> {
	private Model model;
	private String port;
	private final int timeout = 5000;

	public TransmittingEvaluationWorker(Model model, String port) {
		this.model = model;
		this.port = port;
	}

	@Override
	protected Object doInBackground() throws Exception {
		boolean success = false;

		success = model.openSerialConnection(port);
		if (success) {
			success = model.sendCommandToSerial(JavaBleCommunication.REQUESTEVALUATION);
			if (success) {
				model.setTransmittingEvaluation(true);
				StatusBar.setStatus(StatusType.TRANSMITTINGDOJOEVALUATION, "");

				final java.util.Timer timer = new java.util.Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						timer.cancel();
						model.timeoutTransmittingEvaluationWorker();
					}
				}, timeout, timeout);
			}
		}

		return null;
	}
}