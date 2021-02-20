package com.fishingapp.gui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.fishingapp.constants.Constants;
import com.fishingapp.model.Item;



public class TablePanel extends JPanel{
	
	private JTable table;
	private PersonTableModel tableModel;
	
	
	public TablePanel() {
		setBackground(Constants.babyBlue);
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table) , BorderLayout.CENTER);
		
	}
	
	public void setData(List<Item> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
