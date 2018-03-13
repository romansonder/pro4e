package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import utilities.MyBorderFactory;

public class TopView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private GraphicalArea graphicalArea;
	private InputParameterArea inputParameterArea;
	private OutputParameterArea outputParameterArea;

	private Controller controller;

	public TopView(Controller controller) {
		super(new GridBagLayout());

		this.controller = controller;

		graphicalArea = new GraphicalArea(this);
		inputParameterArea = new InputParameterArea(this);
		outputParameterArea = new OutputParameterArea(this);

		graphicalArea.setBorder(MyBorderFactory.createMyBorder("Grafischer Bereich"));
		add(graphicalArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		inputParameterArea.setBorder(MyBorderFactory.createMyBorder("Kommandozentrale"));
		add(inputParameterArea, new GridBagConstraints(1, 0, 1, 2, 0.0, 1.0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		outputParameterArea.setBorder(MyBorderFactory.createMyBorder("Konsole"));
		add(outputParameterArea, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

	}

	@Override
	public void update(Observable obs, Object obj) {
		Model model = (Model) obs;
	}
}