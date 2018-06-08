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
