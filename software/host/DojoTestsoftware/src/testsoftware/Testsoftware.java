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
package testsoftware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import controller.Controller;
import model.Model;
import model.StatusType;
import userinterface.MenuBar;
import userinterface.StatusBar;
import userinterface.TopView;
import utilities.Utility;

public class Testsoftware extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int minWidth = 1000, minheight = 685;
	private static final int width = 1000, height = 685;
	private final String title = "Dojo - Testsoftware";
	private final String dojoIconFileName = "Dojo_Testsoftware_Icon.png";
	private ImageIcon icon = Utility.loadResourceIcon(dojoIconFileName);

	private Model model = new Model();
	private Controller controller = new Controller(model);
	private MenuBar menuBar = new MenuBar(controller);
	private StatusBar statusBar = new StatusBar();
	private TopView topView = new TopView(controller);

	public Testsoftware() {
		setSize(new Dimension(width, height));
		setMinimumSize(new Dimension(minWidth, minheight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		setIconImage(icon.getImage());
		initToolTips();
		setVisible(true);

		controller.setView(topView);
		model.addObserver(topView);

		getContentPane().add(topView, BorderLayout.CENTER);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		setJMenuBar(menuBar);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				controller.closeSerialConnection();
				System.exit(1);
			}
		});
	}

	public void initToolTips() {
		UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
		ToolTipManager.sharedInstance().setDismissDelay(100000);
		ToolTipManager.sharedInstance().setInitialDelay(100);
		StatusBar.setStatus(StatusType.RUNNING, "");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Testsoftware();
			}
		});
	}
}
