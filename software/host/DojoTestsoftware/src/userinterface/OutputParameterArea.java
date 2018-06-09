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

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MuseumsObject;

public class OutputParameterArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel objectID;
	private JLabel name;
	private JLabel language;
	private JLabel path;

	public OutputParameterArea(TopView topView) {
		super(new GridBagLayout());

		objectID = new JLabel("-");
		name = new JLabel("-");
		language = new JLabel("-");
		path = new JLabel("-");

		add(new JLabel("Beacon ID:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(objectID, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Name:"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(name, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Sprache:"), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(language, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Pfad:"), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(path, new GridBagConstraints(1, 3, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(Box.createHorizontalGlue(), new GridBagConstraints(2, 0, 1, 3, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	public void displayObject(MuseumsObject museumsObject) {
		if (null != museumsObject) {
			objectID.setText("" + museumsObject.getID());
			name.setText(museumsObject.getName());
			language.setText(museumsObject.getLanguage());
			path.setText(museumsObject.getPath());
		} else {
			objectID.setText("-");
			name.setText("-");
			language.setText("-");
			path.setText("-");
		}
	}
}
