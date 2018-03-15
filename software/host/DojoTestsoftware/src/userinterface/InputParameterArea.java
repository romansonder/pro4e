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

import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.Museumsobjekt;
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
	private JButton btDeleteObject;
	private JButton btTransmitUSB;
	private JButton btTransmitBT;
	private JButton btEvaluate;
	private JComboBox<AccessRightsTypes> comboAccessRights;
	private JComboBox<LanguagesTypes> comboLanguage;

	public InputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		btHelpLibrary = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLibrary.setToolTipText("<html><b>Bibliothek:</b><br><br>Hier kommt die Beschreibung hin.<br><br></html>");
		btHelpLibrary.setOpaque(false);
		btHelpLibrary.setContentAreaFilled(false);
		btHelpLibrary.setBorderPainted(false);
		btHelpLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLibrary.setMargin(new Insets(0, 0, 0, 0));
		btHelpLibrary.setFocusable(false);

		btHelpAccessRights = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpAccessRights
				.setToolTipText("<html><b>Zutrittsrecht:</b><br><br>Hier kommt die Beschreibung hin.<br><br></html>");
		btHelpAccessRights.setOpaque(false);
		btHelpAccessRights.setContentAreaFilled(false);
		btHelpAccessRights.setBorderPainted(false);
		btHelpAccessRights.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpAccessRights.setMargin(new Insets(0, 0, 0, 0));
		btHelpAccessRights.setFocusable(false);

		btHelpLanguage = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLanguage.setToolTipText("<html><b>Sprache:</b><br><br>Hier kommt die Beschreibung hin.<br><br></html>");
		btHelpLanguage.setOpaque(false);
		btHelpLanguage.setContentAreaFilled(false);
		btHelpLanguage.setBorderPainted(false);
		btHelpLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLanguage.setMargin(new Insets(0, 0, 0, 0));
		btHelpLanguage.setFocusable(false);

		btHelpEvaluate = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpEvaluate
				.setToolTipText("<html><b>Dojo Auswerten:</b><br><br>Hier kommt die Beschreibung hin.<br><br></html>");
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

		btDeleteObject = new JButton("Lösche Objekt");
		btDeleteObject.setEnabled(false);
		btDeleteObject.addActionListener(this);

		btTransmitUSB = new JButton("Übertragen via USB");
		btTransmitUSB.setEnabled(true);
		btTransmitUSB.addActionListener(this);

		btTransmitBT = new JButton("Übertragen via BT");
		btTransmitBT.setEnabled(true);
		btTransmitBT.addActionListener(this);

		btEvaluate = new JButton("Auswerten");
		btEvaluate.setEnabled(true);
		btEvaluate.addActionListener(this);

		comboAccessRights = new JComboBox<>(AccessRightsTypes.values());
		comboLanguage = new JComboBox<>(LanguagesTypes.values());

		add(new JLabel("Bibliothek:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLibrary, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btReadIn, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btNewObject, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btDeleteObject, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btTransmitUSB, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Zutrittsrecht:"), new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpAccessRights, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboAccessRights, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Sprache:"), new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLanguage, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboLanguage, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btTransmitBT, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 11, 2, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Dojo auswerten:"), new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpEvaluate, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btEvaluate, new GridBagConstraints(0, 13, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(Box.createVerticalGlue(), new GridBagConstraints(0, 14, 2, 1, 0.0, 1.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btReadIn) {
			topView.readInObjects();
		} else if (e.getSource() == btNewObject) {
			Museumsobjekt museumsObject = new Museumsobjekt();
			museumsObject.setID(1);
			museumsObject.setName("Mona Lisa 1");
			museumsObject.setPath("C:\\Users\\Tobias\\Desktop\\Mona_Lisa_1.mp3");
			topView.addNewObject(museumsObject);
		} else if (e.getSource() == btDeleteObject) {

		} else if (e.getSource() == btTransmitUSB) {

		} else if (e.getSource() == btTransmitBT) {

		} else if (e.getSource() == btEvaluate) {

		}
	}
}