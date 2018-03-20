package model;

import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class Model extends Observable {
	private Museum museum;

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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);

		if (null != fc.getSelectedFile()) {
			File file = new File(fc.getSelectedFile().getPath());
			Serializer serializer = new Persister();

			Museum museum = new Museum();
			try {
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fc.setFileFilter(filter);
		fc.showSaveDialog(null);

		Serializer serializer = new Persister();
		File file = new File(fc.getSelectedFile().getPath());
		try {
			serializer.write(getMuseum(), file);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		notifyObservers();
	}

	private void setMuseum(Museum museum) {
		this.museum = museum;
		notifyObservers();
	}

	public Museum getMuseum() {
		return this.museum;
	}

	public void addNewObject(Museumsobjekt museumsObject) {
		if (null != museumsObject)
			if (museumsObject.getName() != "" || museumsObject.getPath() != "") {
				this.museum.list.add(museumsObject);
			}
		notifyObservers();
	}
}