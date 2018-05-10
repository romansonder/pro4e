package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import protocol.JavaBleCommunication;
import userinterface.StatusBar;

public class Model extends Observable {
	private Museum museum;
	private SerialPort serialPort;
	private String receivedMessage;
	private File storageDrive;
	private boolean portIsOpened;
	private TransmittingDataWorker transmittingDataWorker;
	private TransmittingPreferencesWorker transmittingPreferencesWorker;

	public Model() {
		museum = new Museum();
	}

	public boolean openSerialConnection(String port) {
		boolean success = false;

		if (null == serialPort) {
			serialPort = new SerialPort(port);

			try {
				if (false == serialPort.isOpened()) {
					serialPort.openPort();

					StatusBar.setStatus(StatusType.OPENEDCONNECTION, serialPort.getPortName());
					portIsOpened = true;
					System.out.println("Port geöffnet");

					serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);

					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
					serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

					success = true;
				}
			} catch (SerialPortException exception) {
				System.out.println("Fehler beim öffnen von Port: " + exception);
				StatusBar.setStatus(StatusType.OPENPORTFAILURE, serialPort.getPortName());
			}
		} else {
			success = true;
		}

		return success;
	}

	public boolean closeSerialConnection() {
		boolean success = false;

		try {
			if (null != serialPort) {
				if (serialPort.isOpened()) {
					serialPort.closePort();
					StatusBar.setStatus(StatusType.PORTCLOSED, serialPort.getPortName());
					portIsOpened = false;
					System.out.println("Port geschlossen");
					success = true;
				}
			}
		} catch (SerialPortException exception) {
			System.out.println("Fehler beim schliessen von Port: " + exception);
			StatusBar.setStatus(StatusType.ClOSEPORTFAILURE, serialPort.getPortName());
		}

		return success;
	}

	public boolean sendCommandToSerial(JavaBleCommunication commandType) {
		boolean success = false;

		try {
			if (false == portIsOpened) {
				StatusBar.setStatus(StatusType.NOOPENCONNECTION, "");
			}

			if (null != serialPort) {
				serialPort.writeBytes(commandType.toCommand().getBytes());
				System.out.println("Command sent: " + commandType);
				success = true;
			}

		} catch (SerialPortException exception) {
			StatusBar.setStatus(StatusType.USERPREFERENCESTRANSMITTINGFAILURE, "");
			System.out.println("Fehler beim senden von String aufgetreten: " + exception);
		}

		return success;
	}

	public boolean readInObjects() {
		boolean success = false;

		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionXml, Definitions.fileExtensionXml);
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);

		if (null != fc.getSelectedFile()) {
			Museum museum = new Museum();
			try {
				File file = new File(fc.getSelectedFile().getPath());
				Serializer serializer = new Persister();
				museum = serializer.read(Museum.class, file);
				success = true;
			} catch (Exception exception) {
				StatusBar.setStatus(StatusType.READOBJECTSFAILURE, "");
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionXml, Definitions.fileExtensionXml);
		fc.setFileFilter(filter);
		fc.showSaveDialog(null);

		File file = fc.getSelectedFile();

		if (null != file) {
			String filePath = file.getAbsolutePath();
			if (!filePath.endsWith("." + Definitions.fileExtensionXml)) {
				file = new File(filePath + "." + Definitions.fileExtensionXml);
			}
			try {
				Serializer serializer = new Persister();
				serializer.write(getMuseum(), file);
				success = true;
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
				success = true;
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
					success = true;
					notifyObservers();
					break;
				}
			}

		return success;
	}

	public boolean transmitMuseumData() {
		boolean success = false;

		try {
			transmittingDataWorker = new TransmittingDataWorker(this, Definitions.driveName);
			transmittingDataWorker.execute();
			success = true;
		} catch (Exception exception) {
			StatusBar.setStatus(StatusType.DATATRANSMITTINGFAILURE, "");
			exception.printStackTrace();
		}

		return success;
	}

	public boolean transmitUserPreferences(String port) {
		boolean success = false;

		try {
			transmittingPreferencesWorker = new TransmittingPreferencesWorker(this, port);
			transmittingPreferencesWorker.execute();
			success = true;
		} catch (Exception exception) {
			StatusBar.setStatus(StatusType.PREFERENCESTRANSMITTINGFAILURE, "");
			exception.printStackTrace();
		}

		return success;
	}

	public boolean evaluateDojo() {
		boolean success = false;

		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionTxt, Definitions.fileExtensionTxt);
		fc.setFileFilter(filter);
		fc.showSaveDialog(null);

		File file = fc.getSelectedFile();

		if (null != file) {
			String filePath = file.getAbsolutePath();
			if (!filePath.endsWith("." + Definitions.fileExtensionTxt)) {
				file = new File(filePath + "." + Definitions.fileExtensionTxt);
			}

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write("The first test line");
				writer.newLine();
				writer.write("The second test line");
				writer.close();
				success = true;
			} catch (Exception exception) {
				StatusBar.setStatus(StatusType.EXTRACTDOJOINFORMATIONFAILURE, "");
				exception.printStackTrace();
			}
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

		if (museum.list.isEmpty()) {
			StatusBar.setStatus(StatusType.NODATATOTRANSMIT, "");
			return success;
		}

		for (int i = 0; i < museum.list.size(); i++) {
			MuseumsObject museumObject = museum.list.get(i);
			File src = new File(museumObject.getPath());
			File dst = new File(driveName.getAbsolutePath() + src.getName());

			try {
				Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
				success = true;
			} catch (NoSuchFileException exception) {
				success = false;
				StatusBar.setStatus(StatusType.DATAFILENOTFOUND, src.getName());
				exception.printStackTrace();
				break;
			} catch (Exception exception) {
				success = false;
				StatusBar.setStatus(StatusType.DATATRANSMITTINGFAILURE, "");
				exception.printStackTrace();
				break;
			}
		}

		return success;
	}

	public Museum getMuseum() {
		return this.museum;
	}

	public File getStorageDrive() {
		return this.storageDrive;
	}

	private void setMuseum(Museum museum) {
		this.museum = museum;
		notifyObservers();
	}

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
		System.out.println("Model: NotifyObserver called");
	}

	public class PortReader implements SerialPortEventListener {
		StringBuilder message = new StringBuilder();

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					byte buffer[] = serialPort.readBytes();
					for (byte oneByte : buffer) {
						if (oneByte == '\n') {
							receivedMessage = message.toString();
							message = new StringBuilder();
							JavaBleCommunication commandType = JavaBleCommunication
									.convertCommandStringToCommandType(receivedMessage);
							HandleReceivedCommand(commandType);
						} else {
							message.append((char) oneByte);
						}
					}

				} catch (SerialPortException exception) {
					System.out.println(exception);
				}
			}
		}

		public void HandleReceivedCommand(JavaBleCommunication commandType) {
			switch (commandType) {
			case COMMANDOENDING:
				System.out.println("Command received: " + JavaBleCommunication.COMMANDOENDING.toString());
				break;
			case REQUESTALIVE:
				System.out.println("Command received: " + JavaBleCommunication.REQUESTALIVE.toString());
				sendCommandToSerial(JavaBleCommunication.ALIVE);
				break;
			case ALIVE:
				System.out.println("Command received: " + JavaBleCommunication.ALIVE.toString());
				break;
			case SENDACCESSRIGHT:
				System.out.println("Command received: " + JavaBleCommunication.SENDACCESSRIGHT.toString());
				break;
			case SENDLANGUAGE:
				System.out.println("Command received: " + JavaBleCommunication.SENDLANGUAGE.toString());
				break;
			default:
				System.out.println("Command received: " + "Unknown command");
				break;
			}
		}
	}
}