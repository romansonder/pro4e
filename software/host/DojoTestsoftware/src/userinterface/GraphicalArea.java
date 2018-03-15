package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Museum;

public class GraphicalArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JTable museumTable;
	private DefaultTableModel tableModel;
	String[] columnNames = { "ID", "Name", "Pfad" };

	public GraphicalArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		museumTable = new JTable(tableModel);
		museumTable.setRowHeight(25);
		museumTable.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(museumTable);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(museumTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 10, 10, 10), 0, 0));
	}

	public void updateMuseumobjekts(Museum museum) {
		museumTable.removeAll();
		String[] columnNames = { "ID", "Name", "Pfad" };
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		for (int i = 0; i < museum.list.size(); i++) {
			Object[] row = new Object[3];
			row[0] = museum.list.get(i).getID();
			row[1] = museum.list.get(i).getName();
			row[2] = museum.list.get(i).getPath();
			tableModel.addRow(row);
		}
		museumTable.setModel(tableModel);
		tableModel.fireTableDataChanged();
	}
}