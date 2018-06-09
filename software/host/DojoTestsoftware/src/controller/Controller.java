/*******************************************************************************
 * Copyright (C) 2018  FHNW Pro4E FS18 Team 3
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package controller;

import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.Model;
import model.MuseumsObject;
import userinterface.TopView;

public class Controller {
	private Model model;
	private TopView topView;

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
		success = model.readInObjects(topView);
		return success;
	}

	public boolean saveObjects() {
		boolean success = false;
		success = model.saveObjects(topView);
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
		success = model.evaluateDojo(port, topView);
		return success;
	}

	public void setView(TopView topView) {
		this.topView = topView;
	}
}
