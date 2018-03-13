package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import utilities.Utility;

public class InputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JButton btHelpLibrary;
	private JButton btHelpAccessRights;
	private JButton btHelpLanguage;
	private JButton btHelpEvaluate;
	private JButton btReadIn;
	private JButton btNewObject;
	private JButton btTransmitUSB;
	private JButton btTransmitBT;
	private JButton btEvaluate;
	private JComboBox comboAccessRights;
	private JComboBox comboLanguage;

	public InputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		btHelpLibrary = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLibrary.setToolTipText(
				"<html><b>Port:</b><br><br>Der genutzte Port der Bluetooth Schnittstelle<br>kann unter Windows 10 via weitere<br>Bluetooth-Optionen unter Bluetooth- und<br>andere Ger�te nachgeschaut werden.<br><br>Ben�tigt wird der ausgehende Port<br>von LogDog_BT.<br><br></html>");
		btHelpLibrary.setOpaque(false);
		btHelpLibrary.setContentAreaFilled(false);
		btHelpLibrary.setBorderPainted(false);
		btHelpLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLibrary.setMargin(new Insets(0, 0, 0, 0));
		btHelpLibrary.setFocusable(false);

		btHelpAccessRights = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpAccessRights.setToolTipText(
				"<html><b>Port:</b><br><br>Der genutzte Port der Bluetooth Schnittstelle<br>kann unter Windows 10 via weitere<br>Bluetooth-Optionen unter Bluetooth- und<br>andere Ger�te nachgeschaut werden.<br><br>Ben�tigt wird der ausgehende Port<br>von LogDog_BT.<br><br></html>");
		btHelpAccessRights.setOpaque(false);
		btHelpAccessRights.setContentAreaFilled(false);
		btHelpAccessRights.setBorderPainted(false);
		btHelpAccessRights.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpAccessRights.setMargin(new Insets(0, 0, 0, 0));
		btHelpAccessRights.setFocusable(false);

		btHelpLanguage = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLanguage.setToolTipText(
				"<html><b>Port:</b><br><br>Der genutzte Port der Bluetooth Schnittstelle<br>kann unter Windows 10 via weitere<br>Bluetooth-Optionen unter Bluetooth- und<br>andere Ger�te nachgeschaut werden.<br><br>Ben�tigt wird der ausgehende Port<br>von LogDog_BT.<br><br></html>");
		btHelpLanguage.setOpaque(false);
		btHelpLanguage.setContentAreaFilled(false);
		btHelpLanguage.setBorderPainted(false);
		btHelpLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLanguage.setMargin(new Insets(0, 0, 0, 0));
		btHelpLanguage.setFocusable(false);

		btHelpEvaluate = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpEvaluate.setToolTipText(
				"<html><b>Port:</b><br><br>Der genutzte Port der Bluetooth Schnittstelle<br>kann unter Windows 10 via weitere<br>Bluetooth-Optionen unter Bluetooth- und<br>andere Ger�te nachgeschaut werden.<br><br>Ben�tigt wird der ausgehende Port<br>von LogDog_BT.<br><br></html>");
		btHelpEvaluate.setOpaque(false);
		btHelpEvaluate.setContentAreaFilled(false);
		btHelpEvaluate.setBorderPainted(false);
		btHelpEvaluate.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpEvaluate.setMargin(new Insets(0, 0, 0, 0));
		btHelpEvaluate.setFocusable(false);

		btReadIn = new JButton("Einlesen");
		btReadIn.setEnabled(true);
		btReadIn.addActionListener(this);

		btNewObject = new JButton("Neues Objekt");
		btNewObject.setEnabled(true);
		btNewObject.addActionListener(this);

		btTransmitUSB = new JButton("�bertragen via USB");
		btTransmitUSB.setEnabled(true);
		btTransmitUSB.addActionListener(this);

		btTransmitBT = new JButton("�bertragen via BT");
		btTransmitBT.setEnabled(true);
		btTransmitBT.addActionListener(this);

		btEvaluate = new JButton("Auswerten");
		btEvaluate.setEnabled(true);
		btEvaluate.addActionListener(this);

		comboAccessRights = new JComboBox();
		comboLanguage = new JComboBox();

		add(new JLabel("Bibliothek:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLibrary, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btReadIn, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btNewObject, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btTransmitUSB, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Zugriffsrecht:"), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpAccessRights, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboAccessRights, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Sprache:"), new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLanguage, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboLanguage, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btTransmitBT, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Dojo auswerten:"), new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpEvaluate, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btEvaluate, new GridBagConstraints(0, 12, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(Box.createVerticalGlue(), new GridBagConstraints(0, 13, 2, 1, 0.0, 1.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btReadIn) {

		} else if (e.getSource() == btNewObject) {

		} else if (e.getSource() == btTransmitUSB) {

		} else if (e.getSource() == btTransmitBT) {

		} else if (e.getSource() == btEvaluate) {

		}
	}
}