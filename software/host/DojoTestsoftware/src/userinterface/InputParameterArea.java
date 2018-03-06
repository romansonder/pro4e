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
	private JComboBox ports;
	private JButton btHelpOptions;
	private JButton connectBT;
	private JButton disconnectBT;
	private JButton syncTimeBT;
	private JButton deleteDataBT;

	public InputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		ports = new JComboBox();
		ports.addActionListener(this);

		btHelpOptions = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpOptions.setToolTipText(
				"<html><b>Port:</b><br><br>Der genutzte Port der Bluetooth Schnittstelle<br>kann unter Windows 10 via weitere<br>Bluetooth-Optionen unter Bluetooth- und<br>andere Geräte nachgeschaut werden.<br><br>Benötigt wird der ausgehende Port<br>von LogDog_BT.<br><br></html>");

		btHelpOptions.setOpaque(false);
		btHelpOptions.setContentAreaFilled(false);
		btHelpOptions.setBorderPainted(false);
		btHelpOptions.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpOptions.setMargin(new Insets(0, 0, 0, 0));
		btHelpOptions.setFocusable(false);

		connectBT = new JButton("Verbindung aufbauen");
		connectBT.setEnabled(true);
		connectBT.addActionListener(this);
		disconnectBT = new JButton("Verbindung trennen");
		disconnectBT.setEnabled(false);
		disconnectBT.addActionListener(this);
		syncTimeBT = new JButton("Synchronisiere Zeit");
		syncTimeBT.setEnabled(false);
		syncTimeBT.addActionListener(this);
		deleteDataBT = new JButton("Log-Daten löschen");
		deleteDataBT.setEnabled(false);
		deleteDataBT.addActionListener(this);

		add(new JLabel("Verbindungsoptionen:"), new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpOptions, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 5), 0, 0));

		add(new JLabel("Port:"), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

		add(ports, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(connectBT, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(disconnectBT, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(Box.createVerticalGlue(), new GridBagConstraints(0, 5, 2, 1, 0.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(syncTimeBT, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(deleteDataBT, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean success = false;
		if (e.getSource() == connectBT) {

		} else if (e.getSource() == disconnectBT) {

		} else if (e.getSource() == syncTimeBT) {

		} else if (e.getSource() == deleteDataBT) {

		}
	}
}