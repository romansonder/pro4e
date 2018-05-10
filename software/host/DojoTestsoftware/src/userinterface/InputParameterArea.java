package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import jssc.SerialPortList;
import model.Definitions;
import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.MuseumsObject;
import utilities.Utility;

public class InputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JButton btHelpLibrary;
	private JButton btHelpAccessRights;
	private JButton btHelpLanguage;
	private JButton btHelpEvaluate;
	private JButton btNewObject;
	private JButton btSave;
	private JButton btTransmitUSB;
	private JButton btTransmitBT;
	private JButton btEvaluate;
	private JComboBox<AccessRightsTypes> comboAccessRights;
	private JComboBox<LanguagesTypes> comboLanguage;
	private JComboBox<String> comboPorts;

	public InputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		btHelpLibrary = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLibrary.setToolTipText(
				"<html><b>Ausstellung:</b><br><br>Einer Ausstellung kann eine beliebige Anzahl<br> an Museumsobjekten hinzugefügt werden.<br><br></html>");
		btHelpLibrary.setOpaque(false);
		btHelpLibrary.setContentAreaFilled(false);
		btHelpLibrary.setBorderPainted(false);
		btHelpLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLibrary.setMargin(new Insets(0, 0, 0, 0));
		btHelpLibrary.setFocusable(false);

		btHelpAccessRights = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpAccessRights.setToolTipText(
				"<html><b>Zutrittsrecht:</b><br><br>Das Zutrittsrecht regelt den erlaubten Zugang<br> zu allen Besichtigungsräumen.<br><br></html>");
		btHelpAccessRights.setOpaque(false);
		btHelpAccessRights.setContentAreaFilled(false);
		btHelpAccessRights.setBorderPainted(false);
		btHelpAccessRights.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpAccessRights.setMargin(new Insets(0, 0, 0, 0));
		btHelpAccessRights.setFocusable(false);

		btHelpLanguage = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLanguage.setToolTipText(
				"<html><b>Sprache:</b><br><br>Spezifiziert in welcher Sprache die Hördateien<br> abgespielt werden.<br><br></html>");
		btHelpLanguage.setOpaque(false);
		btHelpLanguage.setContentAreaFilled(false);
		btHelpLanguage.setBorderPainted(false);
		btHelpLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLanguage.setMargin(new Insets(0, 0, 0, 0));
		btHelpLanguage.setFocusable(false);

		btHelpEvaluate = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpEvaluate.setToolTipText(
				"<html><b>Dojo auswerten:</b><br><br>Wertet Informationen auf dem Dojo aus und<br> legt diese in einer Textdatei ab.<br><br></html>");
		btHelpEvaluate.setOpaque(false);
		btHelpEvaluate.setContentAreaFilled(false);
		btHelpEvaluate.setBorderPainted(false);
		btHelpEvaluate.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpEvaluate.setMargin(new Insets(0, 0, 0, 0));
		btHelpEvaluate.setFocusable(false);

		btNewObject = new JButton("Neues Objekt");
		btNewObject.setEnabled(true);
		btNewObject.addActionListener(this);

		btSave = new JButton("Speichern");
		btSave.setEnabled(true);
		btSave.addActionListener(this);

		btTransmitUSB = new JButton("Übertragen via USB");
		btTransmitUSB.setEnabled(true);
		btTransmitUSB.addActionListener(this);

		btTransmitBT = new JButton("Übertragen");
		btTransmitBT.setEnabled(true);
		btTransmitBT.addActionListener(this);

		btEvaluate = new JButton("Auswerten");
		btEvaluate.setEnabled(true);
		btEvaluate.addActionListener(this);

		comboAccessRights = new JComboBox<>(AccessRightsTypes.values());
		comboLanguage = new JComboBox<>(LanguagesTypes.values());

		String[] portNames = null;
		portNames = SerialPortList.getPortNames();
		comboPorts = new JComboBox<String>(portNames);
		comboPorts.setSelectedIndex(comboPorts.getItemCount() - 1);

		add(new JLabel("Ausstellung:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLibrary, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btNewObject, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btSave, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(btTransmitUSB, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Zutrittsrecht:"), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
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

		add(comboPorts, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 5), 0, 0));

		add(btTransmitBT, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 5, 10, 10), 0, 0));

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

	private MuseumsObject displayNewObjectDialog() {
		MuseumsObject museumsObject = new MuseumsObject();

		JTextField id = new JTextField();
		JTextField name = new JTextField();
		JRadioButton germanRadioBtn = new JRadioButton(Definitions.german, true);
		JRadioButton frenchRadioBtn = new JRadioButton(Definitions.french, false);
		JRadioButton englishRadioBtn = new JRadioButton(Definitions.english, false);
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.add(germanRadioBtn);
		radioButtonPanel.add(frenchRadioBtn);
		radioButtonPanel.add(englishRadioBtn);
		JTextField path = new JTextField();
		path.setEditable(false);
		JButton button = new JButton("Pfad wählen");

		button.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				fc.setCurrentDirectory(workingDirectory);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(Definitions.fileExtensionDescriptionAd4,
						Definitions.fileExtensionAd4);
				fc.setFileFilter(filter);
				fc.setMultiSelectionEnabled(false);
				fc.showOpenDialog(null);
				path.setText(fc.getSelectedFile().getPath());
			}
		});

		germanRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frenchRadioBtn.isSelected() == false && englishRadioBtn.isSelected() == false) {
					germanRadioBtn.setSelected(true);
				}
				frenchRadioBtn.setSelected(!germanRadioBtn.isSelected());
				englishRadioBtn.setSelected(!germanRadioBtn.isSelected());
			}
		});

		frenchRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (germanRadioBtn.isSelected() == false && englishRadioBtn.isSelected() == false) {
					frenchRadioBtn.setSelected(true);
				}
				germanRadioBtn.setSelected(!frenchRadioBtn.isSelected());
				englishRadioBtn.setSelected(!frenchRadioBtn.isSelected());
			}
		});

		englishRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (germanRadioBtn.isSelected() == false && frenchRadioBtn.isSelected() == false) {
					englishRadioBtn.setSelected(true);
				}
				germanRadioBtn.setSelected(!englishRadioBtn.isSelected());
				frenchRadioBtn.setSelected(!englishRadioBtn.isSelected());
			}
		});

		Object[] objects = { "ID", id, "Name", name, "Pfad", radioButtonPanel, button };
		JOptionPane pane = new JOptionPane(objects, JOptionPane.PLAIN_MESSAGE, JOptionPane.CANCEL_OPTION);
		pane.createDialog(null, "Neues Objekt erstellen").setVisible(true);

		try {
			museumsObject.setID(Integer.parseInt(id.getText()));
			museumsObject.setName(name.getText());

			if (germanRadioBtn.isSelected()) {
				museumsObject.setLanguage(Definitions.german);
			} else if (frenchRadioBtn.isSelected()) {
				museumsObject.setLanguage(Definitions.french);
			} else if (englishRadioBtn.isSelected()) {
				museumsObject.setLanguage(Definitions.english);
			} else {
				museumsObject.setLanguage("Unknown");
			}

			museumsObject.setPath(path.getText());
		} catch (Exception exception) {
			museumsObject = null;
		}

		return museumsObject;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btNewObject) {
			MuseumsObject museumObject = displayNewObjectDialog();
			topView.addNewObject(museumObject);
		} else if (event.getSource() == btSave) {
			topView.saveObjects();
		} else if (event.getSource() == btTransmitUSB) {
			topView.transmitMuseumData();
		} else if (event.getSource() == btTransmitBT) {
			boolean success = false;
			success = topView.transmitUserPreferences(comboPorts.getSelectedItem().toString());
			comboPorts.setEnabled(!success);
		} else if (event.getSource() == btEvaluate) {
			topView.evaluateDojo();
		}
	}
}