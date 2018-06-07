package controller;

import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.Model;
import model.MuseumsObject;

public class Controller {
	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	public boolean openSerialConnection(String port) {
		boolean success = false;
		success = model.openSerialConnection(port);
		return success;
	}

	public boolean closeSerialConnection() {
		boolean success = false;
		success = model.closeSerialConnection();
		return success;
	}

	public boolean readInObjects() {
		boolean success = false;
		success = model.readInObjects();
		return success;
	}

	public boolean addNewObject(MuseumsObject museumsObject) {
		boolean success = false;
		success = model.addNewObject(museumsObject);
		return success;
	}

	public boolean deleteObject(MuseumsObject museumsObject) {
		boolean success = false;
		success = model.deleteObject(museumsObject);
		return success;
	}

	public boolean saveObjects() {
		boolean success = false;
		success = model.saveObjects();
		return success;
	}

	public boolean transmitUserPreferences(String port, LanguagesTypes language, AccessRightsTypes accessRight) {
		boolean success = false;
		success = model.transmitUserPreferences(port, language, accessRight);
		return success;
	}

	public boolean transmitMuseumData() {
		boolean success = false;
		success = model.transmitMuseumData();
		return success;
	}

	public boolean evaluateDojo(String port) {
		boolean success = false;
		success = model.evaluateDojo(port);
		return success;
	}
}