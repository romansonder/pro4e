package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MuseumsObject;

public class OutputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel objectID;
	private JLabel name;
	private JLabel path;
	private JButton musicPlayer;

	public OutputParameterArea(TopView topView) {
		super(new GridBagLayout());

		objectID = new JLabel("-");
		name = new JLabel("-");
		path = new JLabel("-");

		musicPlayer = new JButton("Musik Player");

		add(new JLabel("Object ID:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(objectID, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Name:"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(name, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Path:"), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		add(path, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));

		add(Box.createHorizontalGlue(), new GridBagConstraints(2, 0, 1, 3, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(musicPlayer, new GridBagConstraints(3, 0, 1, 3, 0.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));
	}

	public void displayObject(MuseumsObject museumsObject) {
		if (null != museumsObject) {
			objectID.setText("" + museumsObject.getID());
			name.setText(museumsObject.getName());
			path.setText(museumsObject.getPath());
		} else {
			objectID.setText("-");
			name.setText("-");
			path.setText("-");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	}
}