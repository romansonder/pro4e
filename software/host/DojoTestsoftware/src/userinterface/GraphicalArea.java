package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GraphicalArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private TopView topView;

	public GraphicalArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		String[] columnNames = { "ID", "Name", "Pfad" };

		Object[][] data = { { "Kathy", "Smith", "Snowboarding" } };

		JTable table = new JTable(data, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		add(table.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		add(table, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 10, 10, 10), 0, 0));
	}
}