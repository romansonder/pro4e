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

import javax.swing.SwingWorker;

import userinterface.StatusBar;

public class TransmittingDataWorker extends SwingWorker<Object, Object> {
	private Model model;
	private String driveName;

	public TransmittingDataWorker(Model model, String driveName) {
		this.model = model;
		this.driveName = driveName;
	}

	@Override
	protected Object doInBackground() throws Exception {
		boolean success = false;

		StatusBar.setStatus(StatusType.TRANSMITTINGDATA, "");

		success = model.recogniseDriveByDriveName(driveName);
		if (success) {
			success = model.checkMuseumsData();
			if (success) {
				success = model.writeMuseumDataToDrive(model.getStorageDrive());
				if (success) {
					StatusBar.setStatus(StatusType.DATATRANSMITTINGSUCCESSFUL, "");
				}
			}

		} else {
			StatusBar.setStatus(StatusType.DOJODRIVENOTFOUND, "");
		}

		return null;
	}
}
