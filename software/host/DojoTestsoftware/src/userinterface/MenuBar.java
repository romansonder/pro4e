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
	private JMenuItem menuItemLoad, menuItemClose, menuItemAboutUs;
	private Controller controller;

	final String close = "Schliessen";
	final String load = "Laden";
	final String aboutUs = "Über uns";

	public MenuBar(Controller controller) {
		this.controller = controller;

		menu1 = new JMenu("Datei");
		menu1.setMnemonic(KeyEvent.VK_D);

		menu2 = new JMenu("Info");
		menu2.setMnemonic(KeyEvent.VK_I);

		menuItemLoad = new JMenuItem("Ausstellung laden", KeyEvent.VK_A);
		menuItemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		menuItemLoad.setActionCommand(load);
		menuItemLoad.addActionListener(this);
		menu1.add(menuItemLoad);

		menuItemClose = new JMenuItem("Anwendung schliessen", KeyEvent.VK_E);
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItemClose.setActionCommand(close);
		menuItemClose.addActionListener(this);
		menu1.add(menuItemClose);

		menuItemAboutUs = new JMenuItem("Über uns", KeyEvent.VK_U);
		menuItemAboutUs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
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
		} else if (event.getActionCommand().equals(close)) {
			System.exit(1);
		} else if (event.getActionCommand().equals(aboutUs)) {
			AboutUs.aboutUsAnzeigen();
		} else {
			System.out.println(event.getActionCommand());
		}
	}
}