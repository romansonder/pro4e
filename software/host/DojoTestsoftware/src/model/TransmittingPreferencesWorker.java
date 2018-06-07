package model;

import javax.swing.SwingWorker;

import protocol.JavaBleCommunication;
import userinterface.StatusBar;

public class TransmittingPreferencesWorker extends SwingWorker<Object, Object> {
	private Model model;
	private String port;

	public TransmittingPreferencesWorker(Model model, String port) {
		this.model = model;
		this.port = port;
	}

	@Override
	protected Object doInBackground() throws Exception {
		boolean success = false;

		success = model.openSerialConnection(port);
		if (success) {
			success = model.sendCommandToSerial(JavaBleCommunication.SENDACCESSRIGHT);
			if (success) {
				StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, "");
			}
		}

		return null;
	}
}