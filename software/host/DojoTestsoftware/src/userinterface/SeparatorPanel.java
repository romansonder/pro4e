package userinterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SeparatorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Color leftColor;
	protected Color rightColor;

	public SeparatorPanel(Color leftColor, Color rightColor) {
		this.leftColor = leftColor;
		this.rightColor = rightColor;
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		graphics.setColor(leftColor);
		graphics.drawLine(0, 0, 0, getHeight());
		graphics.setColor(rightColor);
		graphics.drawLine(1, 0, 1, getHeight());
	}
}