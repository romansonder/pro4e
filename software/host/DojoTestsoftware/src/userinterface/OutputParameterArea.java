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

public class OutputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JLabel objectID;
	private JLabel name;
	private JLabel path;

	private JButton bt1;

	public OutputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		objectID = new JLabel("27");
		name = new JLabel("Mona Lisa 27");
		path = new JLabel("C:\\Museum\\Objekte\\Mona_Lisa_27.mp3");

		bt1 = new JButton("Musik Player");

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

		add(bt1, new GridBagConstraints(3, 0, 1, 3, 0.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}