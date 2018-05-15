package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Museum;
import model.MuseumsObject;

public class GraphicalArea extends JPanel implements ListSelectionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private TopView topView;
	private JTable museumTable;
	private DefaultTableModel tableModel;
	private final int firstColumnWidth = 80;
	private final int rowHeight = 25;
	private final String[] columnNames = { "Beacon ID", "Name", "Sprache", "Pfad" };

	public GraphicalArea(TopView topView) {
		super(new GridBagLayout());

		this.topView = topView;

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);

		museumTable = new JTable(tableModel);
		museumTable.setRowHeight(rowHeight);
		museumTable.getColumnModel().getColumn(0).setMinWidth(firstColumnWidth);
		museumTable.getColumnModel().getColumn(0).setMaxWidth(firstColumnWidth);
		museumTable.getColumnModel().getColumn(0).setResizable(false);
		museumTable.setBackground(new Color(255, 215, 0));
		museumTable.setOpaque(false);
		museumTable.setAutoCreateRowSorter(true);
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

	private MuseumsObject getSelectedObject() {
		int selectedRow = museumTable.getSelectedRow();
		MuseumsObject museumObject = new MuseumsObject();

		if (-1 != selectedRow) {
			int id = (int) museumTable.getModel().getValueAt(selectedRow, 0);
			String name = museumTable.getModel().getValueAt(selectedRow, 1).toString();
			String language = museumTable.getModel().getValueAt(selectedRow, 2).toString();
			String path = museumTable.getModel().getValueAt(selectedRow, 3).toString();
			museumObject.setID(id);
			museumObject.setName(name);
			museumObject.setLanguage(language);
			museumObject.setPath(path);
		}

		return museumObject;
	}

	public void updateMuseumobjekts(Museum museum) {
		museumTable.clearSelection();
		museumTable.removeAll();
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		for (int i = 0; i < museum.list.size(); i++) {
			Object[] row = new Object[4];
			row[0] = museum.list.get(i).getID();
			row[1] = museum.list.get(i).getName();
			row[2] = museum.list.get(i).getLanguage();
			row[3] = museum.list.get(i).getPath();
			tableModel.addRow(row);
		}

		museumTable.setModel(tableModel);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(museumTable.getModel());
		museumTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();

		museumTable.getColumnModel().getColumn(0).setMinWidth(firstColumnWidth);
		museumTable.getColumnModel().getColumn(0).setMaxWidth(firstColumnWidth);
		museumTable.getColumnModel().getColumn(0).setResizable(false);
		museumTable.getColumnModel().getColumn(1).sizeWidthToFit();
		museumTable.getColumnModel().getColumn(2).sizeWidthToFit();
		museumTable.getColumnModel().getColumn(3).sizeWidthToFit();
		DefaultTableCellRenderer centeringRenderer = new DefaultTableCellRenderer();
		centeringRenderer.setHorizontalAlignment(JLabel.CENTER);
		museumTable.getColumnModel().getColumn(0).setCellRenderer(centeringRenderer);
		museumTable.getColumnModel().getColumn(1).setCellRenderer(centeringRenderer);
		museumTable.getColumnModel().getColumn(2).setCellRenderer(centeringRenderer);
		tableModel.fireTableDataChanged();
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try {
			MuseumsObject museumObject = getSelectedObject();
			topView.displayObject(museumObject);
		} catch (Exception exception) {
			exception.printStackTrace();
			topView.displayObject(null);
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_DELETE) {
			MuseumsObject museumsObject = getSelectedObject();
			if (null != museumsObject) {
				int pressedKey = JOptionPane.showConfirmDialog(null,
						"Möchtest du dieses Museumsobjekt wirklich löschen?", "Museumsobjekt löschen",
						JOptionPane.YES_NO_OPTION);

				if (pressedKey == 0) {
					topView.deleteObject(museumsObject);
				} else {
					// Action was canceled by user.
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}