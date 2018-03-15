package model;

import java.io.File;
import java.util.Observable;

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
		Serializer serializer = new Persister();
		File file = new File("museum.xml");
		Museum museum = new Museum();
		try {
			museum = serializer.read(Museum.class, file);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		setMuseum(museum);
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
		Serializer serializer = new Persister();
		File file = new File("museum.xml");
		this.museum.list.add(museumsObject);

		try {
			serializer.write(museum, file);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		notifyObservers();
	}
}