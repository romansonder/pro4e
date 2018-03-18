package controller;

import model.Model;
import model.Museumsobjekt;
import userinterface.TopView;

public class Controller {
	private Model model;
	private TopView topView;

	public Controller(Model model) {
		this.model = model;
	}

	public void setView(TopView topView) {
		this.topView = topView;
	}

	public void readInObjects() {
		model.readInObjects();
	}

	public void addNewObject(Museumsobjekt museumsObject) {
		model.addNewObject(museumsObject);
	}

	public void saveObjects() {
		model.saveObjects();
	}
}