package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import jssc.SerialPortList;
import model.Definitions;
import model.GuiTypes.AccessRightsTypes;
import model.GuiTypes.LanguagesTypes;
import model.MuseumsObject;
import model.StatusType;
import utilities.Utility;

public class InputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JButton btHelpLibrary;
	private JButton btHelpAccessRights;
	private JButton btHelpLanguage1;
	private JButton btHelpLanguage2;
	private JButton btHelpEvaluate;
	private JButton btHelpSettings;
	private JButton btNewObject;
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
				"<html><b>Ausstellung:</b><br><br>Einer Ausstellung kann eine beliebige Anzahl<br> an Kunstobjekten hinzugefügt werden.<br><br></html>");
		btHelpLibrary.setOpaque(false);
		btHelpLibrary.setContentAreaFilled(false);
		btHelpLibrary.setBorderPainted(false);
		btHelpLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLibrary.setMargin(new Insets(0, 0, 0, 0));
		btHelpLibrary.setFocusable(false);

		btHelpAccessRights = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpAccessRights.setToolTipText(
				"<html><b>Zutrittsrecht:</b><br><br>Das Zutrittsrecht regelt den erlaubten Zugang<br> zu den entsprechenden Besichtigungsräumen.<br><br></html>");
		btHelpAccessRights.setOpaque(false);
		btHelpAccessRights.setContentAreaFilled(false);
		btHelpAccessRights.setBorderPainted(false);
		btHelpAccessRights.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpAccessRights.setMargin(new Insets(0, 0, 0, 0));
		btHelpAccessRights.setFocusable(false);

		btHelpLanguage1 = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLanguage1.setToolTipText(
				"<html><b>Sprache:</b><br><br>Spezifiziert in welcher Sprache die Hördateien<br> abgespielt werden sollen.<br><br></html>");
		btHelpLanguage1.setOpaque(false);
		btHelpLanguage1.setContentAreaFilled(false);
		btHelpLanguage1.setBorderPainted(false);
		btHelpLanguage1.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLanguage1.setMargin(new Insets(0, 0, 0, 0));
		btHelpLanguage1.setFocusable(false);

		btHelpLanguage2 = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpLanguage2.setToolTipText(
				"<html><b>Sprache:</b><br><br>Spezifiziert in welcher Sprache die ausgewählte Hördateien<br> ist.<br><br></html>");
		btHelpLanguage2.setOpaque(false);
		btHelpLanguage2.setContentAreaFilled(false);
		btHelpLanguage2.setBorderPainted(false);
		btHelpLanguage2.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpLanguage2.setMargin(new Insets(0, 0, 0, 0));
		btHelpLanguage2.setFocusable(false);

		btHelpEvaluate = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpEvaluate.setToolTipText(
				"<html><b>Dojo auswerten:</b><br><br>Empfängt alle Beacon IDs die mit dem Dojo<br>geliked wurden und legt diese in einer<br>gewünschten Textdatei ab.<br><br>Damit die Auswertung funktioniert,<br>muss sich der Dojo in der Nähe<br>der Dojo Transmitter Station befinden.<br><br></html>");
		btHelpEvaluate.setOpaque(false);
		btHelpEvaluate.setContentAreaFilled(false);
		btHelpEvaluate.setBorderPainted(false);
		btHelpEvaluate.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpEvaluate.setMargin(new Insets(0, 0, 0, 0));
		btHelpEvaluate.setFocusable(false);

		btHelpSettings = new JButton("", Utility.loadResourceIcon("Dojo_Testsoftware_Help.png"));
		btHelpSettings.setToolTipText(
				"<html><b>Einstellungen:</b><br><br>Unter Windows kann der verwendete Port<br>via Geräte-Manager unter Anschlüsse (COM und LPT)<br>nachgeschaut werden.<br><br>Benötigt wird der Port von JLink CDC UART.<br><br>Damit die Konfiguration funktioniert,<br>muss sich der Dojo in der Nähe<br>der Dojo Transmitter Station befinden.<br><br></html>");
		btHelpSettings.setOpaque(false);
		btHelpSettings.setContentAreaFilled(false);
		btHelpSettings.setBorderPainted(false);
		btHelpSettings.setHorizontalAlignment(SwingConstants.RIGHT);
		btHelpSettings.setMargin(new Insets(0, 0, 0, 0));
		btHelpSettings.setFocusable(false);

		btNewObject = new JButton("Neues Kunstobjekt");
		btNewObject.setEnabled(true);
		btNewObject.addActionListener(this);

		btTransmitUSB = new JButton("Synchronisieren");
		btTransmitUSB.setEnabled(true);
		btTransmitUSB.addActionListener(this);

		btTransmitBT = new JButton("Konfigurieren");
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

		add(btTransmitUSB, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Zutrittsrecht:"), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpAccessRights, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboAccessRights, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Sprache:"), new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpLanguage1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(comboLanguage, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Einstellungen:"), new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(btHelpSettings, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
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
		final int minValue = 0;
		final int maxValue = 999;
		MuseumsObject museumsObject = new MuseumsObject();

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(minValue);
		formatter.setMaximum(maxValue);
		formatter.setAllowsInvalid(false);
		JFormattedTextField id = new JFormattedTextField(formatter);
		id.setToolTipText("" + String.valueOf(minValue) + " \u2264 Eingabe \u2264 " + String.valueOf(maxValue));

		JTextField name = new JTextField();
		name.setToolTipText("Name des Kunstobjektes");
		JRadioButton germanRadioBtn = new JRadioButton(Definitions.german, true);
		JRadioButton frenchRadioBtn = new JRadioButton(Definitions.french, false);
		JRadioButton englishRadioBtn = new JRadioButton(Definitions.english, false);
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.add(germanRadioBtn);
		radioButtonPanel.add(frenchRadioBtn);
		radioButtonPanel.add(englishRadioBtn);
		radioButtonPanel.add(btHelpLanguage2);
		JTextField path = new JTextField();
		path.setEditable(false);
		JButton button = new JButton("Audiodatei auswählen");

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

		Object[] objects = { "Beacon ID", id, "Name", name, "Pfad", radioButtonPanel, button };
		JOptionPane pane = new JOptionPane(objects, JOptionPane.PLAIN_MESSAGE);
		pane.createDialog(null, "Neues Kunstobjekt erstellen").setVisible(true);

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
		} catch (NumberFormatException exception) {
			museumsObject = null;
			StatusBar.setStatus(StatusType.FILLOUTALLFIELDS, "");
		} catch (Exception exception) {
			museumsObject = null;
			StatusBar.setStatus(StatusType.FILLOUTALLFIELDS, "");
		}

		return museumsObject;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btNewObject) {
			MuseumsObject museumObject = displayNewObjectDialog();
			topView.addNewObject(museumObject);
		} else if (event.getSource() == btTransmitUSB) {
			topView.transmitMuseumData();
		} else if (event.getSource() == btTransmitBT) {
			String port = comboPorts.getSelectedItem().toString();
			LanguagesTypes language = (LanguagesTypes) comboLanguage.getSelectedItem();
			AccessRightsTypes accessRight = (AccessRightsTypes) comboAccessRights.getSelectedItem();
			topView.transmitUserPreferences(port, language, accessRight);
		} else if (event.getSource() == btEvaluate) {
			topView.evaluateDojo(comboPorts.getSelectedItem().toString());
		}
	}
}