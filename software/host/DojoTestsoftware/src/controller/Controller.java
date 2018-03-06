package controller;

import model.Model;
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
}