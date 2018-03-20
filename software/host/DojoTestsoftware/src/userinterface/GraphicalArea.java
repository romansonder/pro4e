package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Museum;
import model.Museumsobjekt;

public class GraphicalArea extends JPanel implements ListSelectionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JTable museumTable;
	private DefaultTableModel tableModel;
	private final int firstColumnWidth = 40;
	private final int rowHeight = 25;
	String[] columnNames = { "ID", "Name", "Pfad" };

	public GraphicalArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);

		museumTable = new JTable(tableModel);
		museumTable.setRowHeight(rowHeight);
		museumTable.getColumnModel().getColumn(0).setMaxWidth(firstColumnWidth);
		museumTable.setBackground(new Color(255, 215, 0));
		museumTable.setOpaque(false);
		museumTable.setFillsViewportHeight(true);
		museumTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		museumTable.setDefaultEditor(Object.class, null);
		museumTable.setSelectionBackground(new Color(140, 136, 134));
		museumTable.addKeyListener(this);

		JTableHeader header = museumTable.getTableHeader();
		header.setPreferredSize(new Dimension(100, rowHeight));

		ListSelectionModel rowSelMod = museumTable.getSelectionModel();
		rowSelMod.addListSelectionListener(this);

		JScrollPane scrollPane = new JScrollPane(museumTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(museumTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));
	}

	private Museumsobjekt getSelectedObject() {
		int row = museumTable.getSelectedRow();

		int id = (int) museumTable.getModel().getValueAt(row, 0);
		String name = museumTable.getModel().getValueAt(row, 1).toString();
		String path = museumTable.getModel().getValueAt(row, 2).toString();
		Museumsobjekt museumObject = new Museumsobjekt();
		museumObject.setID(id);
		museumObject.setName(name);
		museumObject.setPath(path);

		return museumObject;
	}

	public void updateMuseumobjekts(Museum museum) {
		museumTable.removeAll();
		museumTable.clearSelection();
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
		museumTable.getColumnModel().getColumn(0).setMaxWidth(firstColumnWidth);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		museumTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tableModel.fireTableDataChanged();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			Museumsobjekt museumObject = getSelectedObject();
			topView.displayObject(museumObject);
		} catch (Exception exception) {
			topView.displayObject(null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Museumsobjekt museumObject = getSelectedObject();
		
		
		int pressedKey = JOptionPane.showConfirmDialog(null, "Möchtest du dieses Museumsobjekt wirklich löschen?",
				"Museumsobjekt löschen", JOptionPane.YES_NO_OPTION);
		
		if(pressedKey == 0){
			topView.deleteObject(museumObject);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}