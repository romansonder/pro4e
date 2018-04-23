package model;

import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import jssc.SerialPort;
import jssc.SerialPortException;
import userinterface.StatusBar;

public class Model extends Observable {
	private Museum museum;
	private final String fileExtensionDescrption = "XML Files (*.xml)";
	private final String fileExtension = "xml";
	public static SerialPort serialPort;
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

				//SendStringToBluetooth("c>");
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

	public void SendStringToBluetooth(String message) {
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
	}

	public void readInObjects() {
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
	}

	public void saveObjects() {
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
	}

	public void addNewObject(MuseumsObject museumsObject) {
		if (null != museumsObject)
			if (museumsObject.getName() != "" || museumsObject.getPath().toString() != "") {
				this.museum.list.add(museumsObject);
				notifyObservers();
			}
	}

	public void deleteObject(MuseumsObject museumsObject) {
		if (null != museumsObject)
			for (MuseumsObject object : this.museum.list) {
				if (object.getID() == museumsObject.getID()) {
					this.museum.list.remove(object);
					System.out.println("Model: Removed object with ID " + object.getID());

					notifyObservers();
					break;
				}
			}
	}

	public Museum getMuseum() {
		return this.museum;
	}

	private void setMuseum(Museum museum) {
		this.museum = museum;
		notifyObservers();
	}
}