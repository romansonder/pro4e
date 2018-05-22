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

		StatusBar.setStatus(StatusType.TRANSMITTINGPREFERENCES, "");

		success = model.openSerialConnection(port);
		if (success) {
			success = model.sendCommandToSerial(JavaBleCommunication.REQUESTALIVE);
			success = model.sendCommandToSerial(JavaBleCommunication.SENDACCESSRIGHT);
			success = model.sendCommandToSerial(JavaBleCommunication.SENDLANGUAGE);
			if (success) {
				StatusBar.setStatus(StatusType.PREFERENCESTRANSMITTINGSUCCESSFUL, "");
			}
		}

		return null;
	}
}