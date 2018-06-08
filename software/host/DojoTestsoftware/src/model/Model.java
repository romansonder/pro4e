package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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
import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import protocol.JavaBleCommunication;
import userinterface.StatusBar;

public class Model extends Observable {
	private Museum museum;
	private SerialPort serialPort;
	private String receivedMessage;
	private File storageDrive;
	private LanguagesTypes selectedLanguage = LanguagesTypes.GERMAN;
	private AccessRightsTypes selectedAccessRight = AccessRightsTypes.LEVEL1;
	private boolean portIsOpened;
	public boolean receivingEvaluation = false;
	public boolean dojoAlive = false;
	List<Integer> likedIDs = new ArrayList<Integer>();

	private String operatingSystem = System.getProperty("os.name").toLowerCase();
	private String loggedInUserName = System.getProperty("user.name");

	private TransmittingDataWorker transmittingDataWorker;
	private TransmittingPreferencesWorker transmittingPreferencesWorker;
	private TransmittingEvaluationWorker transmittingEvaluationWorker;

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

					serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);

					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
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
			StatusBar.setStatus(StatusType.TRANSMITTINGDATAFAILURE, "");
			System.out.println("Fehler beim senden von String aufgetreten: " + exception);
		}

		return success;
	}

	public boolean readInObjects() {
		boolean success = false;

		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionXml,
				Definitions.fileExtensionXml);
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionXml,
				Definitions.fileExtensionXml);
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

		if (null != museumsObject) {
			if (!museumsObject.getName().equals("") && !museumsObject.getPath().equals("")) {

				for (MuseumsObject object : this.museum.list) {
					if (object.getID() == museumsObject.getID()) {

						if (object.getLanguage().equals(museumsObject.getLanguage())) {
							StatusBar.setStatus(StatusType.LANGUAGEDUPLICATE, "Beacon ID: " + museumsObject.getID()
									+ "; Sprache: " + museumsObject.getLanguage());
							success = false;
							return success;
						}
					}
				}

				this.museum.list.add(museumsObject);
				success = true;
				StatusBar.setStatus(StatusType.MUSEUMSOBJECTSUCCESSFULLCREATED, "");
				notifyObservers();
			} else {
				StatusBar.setStatus(StatusType.FILLOUTALLFIELDS, "");
			}
		}

		return success;
	}

	public boolean deleteObject(MuseumsObject museumsObject) {
		boolean success = false;

		if (null != museumsObject)
			for (MuseumsObject object : this.museum.list) {
				if (object.getID() == museumsObject.getID()) {
					this.museum.list.remove(object);
					System.out.println("Model: Removed object with Beacon ID " + object.getID());
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

	public boolean transmitUserPreferences(String port, LanguagesTypes language, AccessRightsTypes accessRight) {
		boolean success = false;

		try {
			dojoAlive = false;
			selectedLanguage = language;
			selectedAccessRight = accessRight;
			transmittingPreferencesWorker = new TransmittingPreferencesWorker(this, port);
			transmittingPreferencesWorker.execute();
			success = true;
		} catch (Exception exception) {
			StatusBar.setStatus(StatusType.PREFERENCESTRANSMITTINGFAILURE, "");
			exception.printStackTrace();
		}

		return success;
	}

	public void setTransmittingEvaluation(boolean transmitting) {
		receivingEvaluation = transmitting;
	}

	public boolean evaluateDojo(String port) {
		boolean success = false;

		try {
			dojoAlive = false;
			likedIDs = new ArrayList<Integer>();
			transmittingEvaluationWorker = new TransmittingEvaluationWorker(this, port);
			transmittingEvaluationWorker.execute();
			success = true;
		} catch (Exception exception) {
			StatusBar.setStatus(StatusType.PREFERENCESTRANSMITTINGFAILURE, "");
			exception.printStackTrace();
		}

		return success;
	}

	public boolean evaluateDojoToFile() {
		boolean success = false;

		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionTxt,
				Definitions.fileExtensionTxt);
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
				writer.write("Folgende Beacon IDs wurden vom Besucher geliked:");
				writer.newLine();
				writer.newLine();

				for (int i = 0; i < likedIDs.size(); i++) {
					writer.write("Beacon ID: " + likedIDs.get(i));
					writer.newLine();
				}

				writer.newLine();
				writer.write("Beste Grüsse Team 3 Pro4E FS18.");
				writer.close();
				success = true;
			} catch (Exception exception) {
				StatusBar.setStatus(StatusType.EXTRACTDOJOINFORMATIONFAILURE, "");
				exception.printStackTrace();
			}
		}

		return success;
	}

	private boolean isWindows() {
		return (operatingSystem.indexOf("win") >= 0);
	}

	private boolean isUnix() {
		return (operatingSystem.indexOf("nix") >= 0 || operatingSystem.indexOf("nux") >= 0
				|| operatingSystem.indexOf("aix") > 0);
	}

	public boolean recogniseDriveByDriveName(String driveName) {
		boolean success = false;

		if (true == isWindows()) {
			System.out.println("Windows detected!");

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
		} else if (true == isUnix()) {
			System.out.println("Unix or Linux detected!");

			File drive = new File("//media//" + loggedInUserName + "//" + driveName);

			if (drive.exists() && drive.isDirectory()) {
				storageDrive = drive;
				success = true;
			}
		} else {
			System.out.println("Unknown OS detected!");
		}

		return success;
	}

	public boolean checkMuseumsData() {
		boolean success = false;

		if (museum.list.isEmpty()) {
			StatusBar.setStatus(StatusType.NODATATOTRANSMIT, "");
			return success;
		}

		for (int i = 0; i < museum.list.size(); i++) {
			MuseumsObject museumsObject = museum.list.get(i);

			boolean germanAvailable = false;
			boolean frenchAvailable = false;
			boolean englishAvailable = false;

			for (MuseumsObject otherObject : this.museum.list) {
				if (museumsObject.getID() == otherObject.getID()) {
					if (otherObject.getLanguage().equals(Definitions.german)) {
						germanAvailable = true;
					} else if (otherObject.getLanguage().equals(Definitions.french)) {
						frenchAvailable = true;
					} else if (otherObject.getLanguage().equals(Definitions.english)) {
						englishAvailable = true;
					}

					if (germanAvailable && frenchAvailable && englishAvailable) {
						success = true;
						break;
					} else {
						success = false;
					}

				}
			}

			if (!success) {
				String missingLanguage = "Unknown";
				if (!germanAvailable) {
					missingLanguage = Definitions.german;
				} else if (!frenchAvailable) {
					missingLanguage = Definitions.french;
				} else if (!englishAvailable) {
					missingLanguage = Definitions.english;
				}

				StatusBar.setStatus(StatusType.LANGUAGEMISSING,
						"Beacon ID: " + museumsObject.getID() + "; Sprache: " + missingLanguage);
				break;
			}
		}

		return success;
	}

	public boolean writeMuseumDataToDrive(File drivePath) {
		boolean success = false;

		for (int i = 0; i < museum.list.size(); i++) {
			MuseumsObject museumObject = museum.list.get(i);
			int languageIndex = 0;
			if (museumObject.getLanguage().equals(Definitions.german)) {
				languageIndex = 1;
			} else if (museumObject.getLanguage().equals(Definitions.french)) {
				languageIndex = 2;
			} else if (museumObject.getLanguage().equals(Definitions.english)) {
				languageIndex = 3;
			} else {
				languageIndex = 0;
			}

			File src = new File(museumObject.getPath());
			String newFileName = String.format("%03d" + languageIndex, museumObject.getID()) + "."
					+ Definitions.fileExtensionAd4;
			File dst = new File(drivePath.getAbsolutePath() + "//" + newFileName);
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

	public void timeoutTransmittingEvaluationWorker() {
		if (false == dojoAlive) {
			StatusBar.setStatus(StatusType.DOJONOTRESPONDING, "");
			System.out.println("Timeout of TransmittingEvaluationWorker");
		}
	}

	public void timeoutTransmittingPreferencesWorker() {
		if (false == dojoAlive) {
			StatusBar.setStatus(StatusType.DOJONOTRESPONDING, "");
			System.out.println("Timeout of TransmittingPreferencesWorker");
		}
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
							HandleReceivedCommand(commandType, receivedMessage);
						} else {
							message.append((char) oneByte);
						}
					}
				} catch (SerialPortException exception) {
					System.out.println(exception);
				}
			}
		}

		public void HandleReceivedCommand(JavaBleCommunication commandType, String receivedMessage) {
			dojoAlive = true;
			switch (commandType) {
			case COMMANDOENDING:
				System.out.println("Command received: " + JavaBleCommunication.COMMANDOENDING.toString());
				break;
			case ACCESSLEVEL1:
				System.out.println("Command received: " + JavaBleCommunication.ACCESSLEVEL1.toString());
				break;
			case ACCESSLEVEL2:
				System.out.println("Command received: " + JavaBleCommunication.ACCESSLEVEL2.toString());
				break;
			case ACCESSLEVEL3:
				System.out.println("Command received: " + JavaBleCommunication.ACCESSLEVEL3.toString());
				break;
			case ACCESSLEVEL4:
				System.out.println("Command received: " + JavaBleCommunication.ACCESSLEVEL4.toString());
				break;
			case ACCESSLEVEL5:
				System.out.println("Command received: " + JavaBleCommunication.ACCESSLEVEL5.toString());
				break;
			case LANGUAGEGERMAN:
				System.out.println("Command received: " + JavaBleCommunication.LANGUAGEGERMAN.toString());
				break;
			case LANGUAGEENGLISH:
				System.out.println("Command received: " + JavaBleCommunication.LANGUAGEENGLISH.toString());
				break;
			case LANGUAGEFRENCH:
				System.out.println("Command received: " + JavaBleCommunication.LANGUAGEFRENCH.toString());
				break;
			case REQUESTALIVE:
				System.out.println("Command received: " + JavaBleCommunication.REQUESTALIVE.toString());
				break;
			case AKNOWLEDGE:
				System.out.println("Command received: " + JavaBleCommunication.AKNOWLEDGE.toString());
				if (receivingEvaluation) {
					receivingEvaluation = false;
					evaluateDojoToFile();
					System.out.println("Receiving of evaluation successful completed.");
					StatusBar.setStatus(StatusType.EVALUATIONSUCCESSFUL, "");
					break;
				} else {
					StatusBar.setStatus(StatusType.PREFERENCESTRANSMITTINGSUCCESSFUL, "");
					break;
				}
			case SENDACCESSRIGHT:
				System.out.println("Command received: " + JavaBleCommunication.SENDACCESSRIGHT.toString());
				break;
			case SENDLANGUAGE:
				System.out.println("Command received: " + JavaBleCommunication.SENDLANGUAGE.toString());
				break;
			case REQUESTLANGUAGE:
				System.out.println("Command received: " + JavaBleCommunication.REQUESTLANGUAGE.toString());
				switch (selectedLanguage) {
				case GERMAN:
					sendCommandToSerial(JavaBleCommunication.LANGUAGEGERMAN);
					break;
				case ENGLISH:
					sendCommandToSerial(JavaBleCommunication.LANGUAGEENGLISH);
					break;
				case FRENCH:
					sendCommandToSerial(JavaBleCommunication.LANGUAGEFRENCH);
					break;
				}
				break;
			case REQUESTACCESSLEVEL:
				System.out.println("Command received: " + JavaBleCommunication.REQUESTACCESSLEVEL.toString());
				switch (selectedAccessRight) {
				case LEVEL1:
					sendCommandToSerial(JavaBleCommunication.ACCESSLEVEL1);
					break;
				case LEVEL2:
					sendCommandToSerial(JavaBleCommunication.ACCESSLEVEL2);
					break;
				case LEVEL3:
					sendCommandToSerial(JavaBleCommunication.ACCESSLEVEL3);
					break;
				case LEVEL4:
					sendCommandToSerial(JavaBleCommunication.ACCESSLEVEL4);
					break;
				case LEVEL5:
					sendCommandToSerial(JavaBleCommunication.ACCESSLEVEL5);
					break;
				}
				break;
			case UNKNOWNCOMMAND:
				System.out.println("Command received: " + JavaBleCommunication.UNKNOWNCOMMAND.toString());
				if (true == receivingEvaluation) {
					String idNumber = receivedMessage.toString().replaceAll("\\D+", "");
					int likedID = Integer.parseInt(idNumber);
					boolean alreadyLiked = likedIDs.contains(likedID);
					if (false == alreadyLiked) {
						likedIDs.add(likedID);
					}
					System.out.println("Received liked ID: " + likedID);
				}
				break;
			default:
				break;
			}
		}
	}
}