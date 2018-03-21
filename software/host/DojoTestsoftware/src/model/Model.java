package model;

import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class Model extends Observable {
	private Museum museum;
	private final String fileExtensionDescrption = "XML Files (*.xml)";
	private final String fileExtension = "xml";

	public Model() {
		museum = new Museum();
	}

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
		System.out.println("Model: NotifyObserver called");
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