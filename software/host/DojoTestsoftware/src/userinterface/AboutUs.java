package userinterface;

import java.awt.Dimension;
import java.awt.Font;
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
	private static ImageIcon icon = Utility.loadResourceIcon("Dojo_Testsoftware_Icon.png");

	public static void aboutUsAnzeigen() {
		final String title = "Über uns";
		final int width = 570;
		final int height = 420;

		final JDialog aboutUsDialog = new JDialog(null, title, ModalityType.APPLICATION_MODAL);
		aboutUsDialog.setSize(new Dimension(width, height));
		aboutUsDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutUsDialog.setLocationRelativeTo(null);
		aboutUsDialog.setResizable(false);
		aboutUsDialog.setIconImage(icon.getImage());
		aboutUsDialog.setLayout(new GridBagLayout());

		ImageIcon backgroundImage = Utility.loadResourceIcon("Dojo_Testsoftware_Logo.png");
		backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(340, 200, Image.SCALE_DEFAULT));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setSize(new Dimension(width, height));

		JLabel swName = new JLabel("Dojo - Testsoftware");
		swName.setFont(new Font("Arial", Font.BOLD, 25));

		aboutUsDialog.add(swName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		aboutUsDialog.add(backgroundLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
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