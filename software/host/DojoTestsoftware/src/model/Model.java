package model;

import java.util.Observable;

public class Model extends Observable {

	public Model() {

	}

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
		System.out.println("Model: NotifyObserver called");
	}
}