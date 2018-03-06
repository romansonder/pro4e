package userinterface;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class OutputParameterArea extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;

	public OutputParameterArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}