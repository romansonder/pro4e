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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Utility;

public class AboutUs extends JDialog {
	private static final long serialVersionUID = 1L;
	private final static String title = "Über uns";
	private final static String dojoIconFileName = "Dojo_Testsoftware_Icon.png";
	private final static String dojoLogoFileName = "Dojo_Testsoftware_Logo.png";
	private static final int width = 570, height = 500;

	public static void aboutUsAnzeigen() {
		final JDialog aboutUsDialog = new JDialog(null, title, ModalityType.APPLICATION_MODAL);
		aboutUsDialog.setSize(new Dimension(width, height));
		aboutUsDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutUsDialog.setLocationRelativeTo(null);
		aboutUsDialog.setResizable(false);
		ImageIcon icon = Utility.loadResourceIcon(dojoIconFileName);
		aboutUsDialog.setIconImage(icon.getImage());
		aboutUsDialog.setLayout(new GridBagLayout());

		ImageIcon backgroundImage = Utility.loadResourceIcon(dojoLogoFileName);
		backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(340, 300, Image.SCALE_DEFAULT));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setSize(new Dimension(width, height));

		aboutUsDialog.add(backgroundLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(new GridBagLayout());

		teamPanel.add(new JLabel("Projektleiter:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));

		teamPanel.add(new JLabel("Hiltbrunner Dominik"), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));

		teamPanel.add(new JLabel("Teammitglieder:"), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		teamPanel.add(new JLabel("Klenke Tobias"), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		teamPanel.add(new JLabel("Lattman Emerson"), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		teamPanel.add(new JLabel("Ochs Pius"), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		teamPanel.add(new JLabel("Sonder Roman"), new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		teamPanel.add(new JLabel("Stutz Alexander"), new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));

		aboutUsDialog.add(teamPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

		aboutUsDialog.setVisible(true);
	}
}
