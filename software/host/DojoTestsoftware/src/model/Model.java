package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import jssc.SerialPort;
import jssc.SerialPortException;
import userinterface.StatusBar;

public class Model extends Observable {
	private Museum museum;
	private final String fileExtensionDescrption = "XML Files (*.xml)";
	private final String fileExtension = "xml";
	private final String driveName = "SANDISK";
	public static SerialPort serialPort;
	public static File storageDrive;
	public static boolean portIsOpened;

	public Model() {
		museum = new Museum();
	}

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
		System.out.println("Model: NotifyObserver called");
	}

	public boolean OpenBluetoothConnection(String port) {
		boolean success = false;

		serialPort = new SerialPort(port);
		try {
			if (false == serialPort.isOpened()) {
				serialPort.openPort();

				StatusBar.setStatus(StatusType.OPENEDCONNECTION, serialPort.getPortName());
				portIsOpened = true;
				System.out.println("Verbindung geöffnet");

				serialPort.setParams(SerialPort.BAUDRATE_38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

				// SendStringToBluetooth("c>");
				success = true;
				notifyObservers();
			}

		} catch (SerialPortException ex) {
			System.out.println("Fehler beim öffnen von Port: " + ex);
			StatusBar.setStatus(StatusType.OPENPORTFAILURE, serialPort.getPortName());
		}

		return success;
	}

	public boolean CloseBluetoothConnection() {
		boolean success = false;

		try {
			if (null != serialPort) {
				if (serialPort.isOpened()) {
					serialPort.closePort();
					StatusBar.setStatus(StatusType.PORTCLOSED, serialPort.getPortName());
					portIsOpened = false;
					System.out.println("Verbindung geschlossen");
					success = true;
				}
			}
		} catch (SerialPortException ex) {
			System.out.println("Fehler beim chliessen von Port: " + ex);
			StatusBar.setStatus(StatusType.ClOSEPORTFAILURE, serialPort.getPortName());
		}

		return success;
	}

	public boolean SendStringToBluetooth(String message) {
		boolean success = false;
		try {
			if (false == portIsOpened) {
				StatusBar.setStatus(StatusType.NOOPENCONNECTION, "");
			}

			if (null != serialPort) {
				serialPort.writeBytes(message.getBytes());
				System.out.println(message + " gesendet");
				notifyObservers();
			}

		} catch (SerialPortException ex) {
			System.out.println("Fehler beim senden von String aufgetreten: " + ex);
		}

		return success;
	}

	public boolean readInObjects() {
		boolean success = false;

		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileExtensionDescrption, fileExtension);
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);

		if (null != fc.getSelectedFile()) {
			Museum museum = new Museum();
			try {
				File file = new File(fc.getSelectedFile().getPath());
				Serializer serializer = new Persister();
				museum = serializer.read(Museum.class, file);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			setMuseum(museum);
			notifyObservers();
		}

		return success;
	}

	public boolean saveObjects() {
		boolean success = false;
		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileExtensionDescrption, fileExtension);
		fc.setFileFilter(filter);
		fc.showSaveDialog(null);

		File file = fc.getSelectedFile();

		if (null != file) {
			String filePath = file.getAbsolutePath();
			if (!filePath.endsWith("." + fileExtension)) {
				file = new File(filePath + "." + fileExtension);
			}
			try {
				Serializer serializer = new Persister();
				serializer.write(getMuseum(), file);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			notifyObservers();
		}

		return success;
	}

	public boolean addNewObject(MuseumsObject museumsObject) {
		boolean success = false;
		if (null != museumsObject)
			if (museumsObject.getName() != "" || museumsObject.getPath().toString() != "") {
				this.museum.list.add(museumsObject);
				notifyObservers();
			}

		return success;
	}

	public boolean deleteObject(MuseumsObject museumsObject) {
		boolean success = false;
		if (null != museumsObject)
			for (MuseumsObject object : this.museum.list) {
				if (object.getID() == museumsObject.getID()) {
					this.museum.list.remove(object);
					System.out.println("Model: Removed object with ID " + object.getID());

					notifyObservers();
					break;
				}
			}

		return success;
	}

	public boolean transmitMuseumData() {
		boolean success = false;

		success = recogniseDriveByDriveName(driveName);
		if (success) {
			success = writeMuseumDataToDrive(storageDrive);
		}

		return success;
	}

	public boolean testBluetooth() {
		boolean success = false;

		success = OpenBluetoothConnection("COM1");
		if (success) {
			// success = SendStringToBluetooth("");
			success = CloseBluetoothConnection();
		}

		return success;
	}

	public boolean recogniseDriveByDriveName(String driveName) {
		boolean success = false;

		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] fileList = File.listRoots();

		for (int i = 0; i < fileList.length; i++) {
			if (fsv.getSystemDisplayName(fileList[i]).contains(driveName)) {
				if (fsv.isDrive(fileList[i])) {
					System.out.println("Display name: " + fsv.getSystemDisplayName(fileList[i]));
					System.out.println("Absolute path: " + fileList[i].getAbsolutePath());
					storageDrive = fileList[i];
					success = true;
					break;
				}
			}
		}

		return success;
	}

	public boolean writeMuseumDataToDrive(File driveName) {
		boolean success = false;

		for (int i = 0; i < museum.list.size(); i++) {
			MuseumsObject museumObject = museum.list.get(i);
			System.out.println(museumObject.getID());
			System.out.println(museumObject.getName());
			System.out.println(museumObject.getPath());

			File src = new File(museumObject.getPath());
			File dst = new File(driveName.getAbsolutePath() + src.getName());

			try {
				Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
				success = true;
			} catch (IOException e) {
				success = false;
				e.printStackTrace();
			}
		}

		return success;
	}

	public Museum getMuseum() {
		return this.museum;
	}

	private void setMuseum(Museum museum) {
		this.museum = museum;
		notifyObservers();
	}
}