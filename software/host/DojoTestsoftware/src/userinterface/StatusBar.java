package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GuiTypes.StatusType;
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

	/**
	 * Setzt neue Statusmeldung in der Statusbar.
	 * 
	 * @param statusType
	 * @param message
	 */
	public static void setStatus(StatusType statusType, String message) {
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