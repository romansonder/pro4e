package controller;

import model.Model;
import model.MuseumsObject;

public class Controller {
	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	public void readInObjects() {
		model.readInObjects();
	}

	public void addNewObject(MuseumsObject museumsObject) {
		model.addNewObject(museumsObject);
	}

	public void deleteObject(MuseumsObject museumsObject) {
		model.deleteObject(museumsObject);
	}

	public void saveObjects() {
		model.saveObjects();
	}
}