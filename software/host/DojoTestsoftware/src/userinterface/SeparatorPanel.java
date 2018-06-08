/*******************************************************************************
 * Copyright (C) 2018  FHNW Pro4E FS18 Team 3
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
