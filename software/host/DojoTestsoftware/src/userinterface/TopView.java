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
package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Controller;
import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.Model;
import model.MuseumsObject;
import utilities.MyBorderFactory;

public class TopView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private GraphicalArea graphicalArea;
	private InputParameterArea inputParameterArea;
	private OutputParameterArea outputParameterArea;

	private Controller controller;

	public TopView(Controller controller) {
		super(new GridBagLayout());

		this.controller = controller;

		graphicalArea = new GraphicalArea(this);
		inputParameterArea = new InputParameterArea(this);
		outputParameterArea = new OutputParameterArea(this);

		graphicalArea.setBorder(MyBorderFactory.createMyBorder("Ausstellung"));
		add(graphicalArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));

		inputParameterArea.setBorder(MyBorderFactory.createMyBorder("Kommandozentrale"));
		add(inputParameterArea, new GridBagConstraints(1, 0, 1, 2, 0.0, 1.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 5, 10, 10), 0, 0));

		outputParameterArea.setBorder(MyBorderFactory.createMyBorder("Information"));
		add(outputParameterArea, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.BOTH, new Insets(5, 10, 10, 5), 0, 0));
	}

	public boolean readInObjects() {
		boolean success = false;
		success = controller.readInObjects();
		return success;
	}

	public boolean addNewObject(MuseumsObject museumsObject) {
		boolean success = false;
		success = controller.addNewObject(museumsObject);
		return success;
	}

	public boolean deleteObject(MuseumsObject museumsObject) {
		boolean success = false;
		success = controller.deleteObject(museumsObject);
		return success;
	}

	public boolean saveObjects() {
		boolean success = false;
		success = controller.saveObjects();
		return success;
	}

	public boolean transmitUserPreferences(String port, LanguagesTypes language, AccessRightsTypes accessRight) {
		boolean success = false;
		success = controller.transmitUserPreferences(port, language, accessRight);
		return success;
	}

	public boolean transmitMuseumData() {
		boolean success = false;
		success = controller.transmitMuseumData();
		return success;
	}

	public boolean evaluateDojo(String port) {
		boolean success = false;
		success = controller.evaluateDojo(port);
		return success;
	}

	public void displayObject(MuseumsObject museumsObject) {
		outputParameterArea.displayObject(museumsObject);
	}

	@Override
	public void update(Observable obs, Object obj) {
		Model model = (Model) obs;
		graphicalArea.updateMuseumobjekts(model.getMuseum());
	}
}
