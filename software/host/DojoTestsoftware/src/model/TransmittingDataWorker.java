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
			success = model.writeMuseumDataToDrive(model.getStorageDrive());
			if (success) {
				StatusBar.setStatus(StatusType.DATATRANSMITTINGSUCCESSFUL, "");
			}
		} else {
			StatusBar.setStatus(StatusType.DOJODRIVENOTFOUND, "");
		}

		return null;
	}
}