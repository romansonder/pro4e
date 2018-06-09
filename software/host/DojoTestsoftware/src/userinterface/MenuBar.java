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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.Controller;

public class MenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JMenu menu1;
	private JMenu menu2;
	private JMenuItem menuItemLoad, menuItemSave, menuItemClose, menuItemAboutUs;
	private Controller controller;

	final String close = "Schliessen";
	final String load = "Laden";
	final String save = "Speichern";
	final String aboutUs = "Über uns";

	public MenuBar(Controller controller) {
		this.controller = controller;

		menu1 = new JMenu("Datei");
		menu1.setMnemonic(KeyEvent.VK_D);

		menu2 = new JMenu("Info");
		menu2.setMnemonic(KeyEvent.VK_I);

		menuItemLoad = new JMenuItem("Ausstellung laden", KeyEvent.VK_L);
		menuItemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menuItemLoad.setActionCommand(load);
		menuItemLoad.addActionListener(this);
		menu1.add(menuItemLoad);

		menuItemSave = new JMenuItem("Ausstellung speichern", KeyEvent.VK_S);
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItemSave.setActionCommand(save);
		menuItemSave.addActionListener(this);
		menu1.add(menuItemSave);

		menuItemClose = new JMenuItem("Anwendung schliessen", KeyEvent.VK_Q);
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuItemClose.setActionCommand(close);
		menuItemClose.addActionListener(this);
		menu1.add(menuItemClose);

		menuItemAboutUs = new JMenuItem("Über uns", KeyEvent.VK_U);
		menuItemAboutUs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		menuItemAboutUs.setActionCommand(aboutUs);
		menuItemAboutUs.addActionListener(this);
		menu2.add(menuItemAboutUs);

		add(menu1);
		add(menu2);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(load)) {
			controller.readInObjects();
		} else if (event.getActionCommand().equals(save)) {
			controller.saveObjects();
		} else if (event.getActionCommand().equals(close)) {
			System.exit(1);
		} else if (event.getActionCommand().equals(aboutUs)) {
			AboutUs.aboutUsAnzeigen();
		} else {
			System.out.println(event.getActionCommand());
		}
	}
}
