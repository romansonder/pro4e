package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OutputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JTextField transmitTextField;
	public JTextField receiveTextField;
	private JButton sendButton;

	public OutputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		transmitTextField = new JTextField("");
		receiveTextField = new JTextField("");
		receiveTextField.setEditable(false);
		sendButton = new JButton("Senden");
		sendButton.addActionListener(this);

		add(new JLabel("Senden:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(transmitTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(sendButton, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(new JLabel("Empfangen:"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		add(receiveTextField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton) {
		}
	}
}