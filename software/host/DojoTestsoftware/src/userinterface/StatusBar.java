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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.StatusType;
import utilities.TimerThread;

public final class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;

	private static TimerThread timerThread;
	private static JPanel leftPanel;
	private static JLabel statusLabel = new JLabel();
	private JPanel rightPanel;

	public StatusBar() {
		createStatusBarPanels();
		setStatus(StatusType.RUNNING, "");
		setDateTime();
	}

	public static void setStatus(StatusType statusType, String message) {
		if (statusType == StatusType.TRANSMITTINGDATA) {
			if (null != timerThread) {
				timerThread.setTransmittingRunning(true);
			}

		} else if (statusType == StatusType.TRANSMITTINGPREFERENCES) {
			if (null != timerThread) {
				timerThread.setPreferencesTransmittingRunning(true);
			}

		} else if (statusType == StatusType.TRANSMITTINGDOJOEVALUATION) {
			if (null != timerThread) {
				timerThread.setEvaluationRunning(true);
			}

		} else {
			if (null != timerThread) {
				timerThread.setTransmittingRunning(false);
				timerThread.setPreferencesTransmittingRunning(false);
				timerThread.setEvaluationRunning(false);
			}
		}

		setLeftComponent(statusLabel);
		statusLabel.setText(statusType.toString() + message);
	}

	private void setDateTime() {
		final JLabel dateLabel = new JLabel();
		dateLabel.setHorizontalAlignment(JLabel.CENTER);
		setRightComponent(dateLabel);

		final JLabel timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		setRightComponent(timeLabel);

		timerThread = new TimerThread(dateLabel, timeLabel);
		timerThread.start();
	}

	private void createStatusBarPanels() {
		final int height = 23;

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(getWidth(), height));

		leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 3));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 1, 3));
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);
	}

	private static void setLeftComponent(JComponent component) {
		if (null != leftPanel) {
			leftPanel.add(component);
		}
	}

	private void setRightComponent(JComponent component) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
		panel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
		panel.add(component);
		rightPanel.add(panel);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		int y = 0;
		graphics.setColor(new Color(156, 154, 140));
		graphics.drawLine(0, y, getWidth(), y);
		y++;

		graphics.setColor(new Color(196, 194, 183));
		graphics.drawLine(0, y, getWidth(), y);
		y++;

		graphics.setColor(new Color(218, 215, 201));
		graphics.drawLine(0, y, getWidth(), y);
		y++;

		graphics.setColor(new Color(233, 231, 217));
		graphics.drawLine(0, y, getWidth(), y);

		y = getHeight() - 3;

		graphics.setColor(new Color(233, 232, 218));
		graphics.drawLine(0, y, getWidth(), y);
		y++;

		graphics.setColor(new Color(233, 231, 216));
		graphics.drawLine(0, y, getWidth(), y);
		y++;

		graphics.setColor(new Color(221, 221, 220));
		graphics.drawLine(0, y, getWidth(), y);
	}
}
