package userinterface;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GraphicalArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private TopView topView;

	public GraphicalArea(TopView topView) {
		super(new BorderLayout());

		this.topView = topView;
	}
}