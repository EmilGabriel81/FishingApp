 package com.fishingapp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fishingapp.model.Item;


public class PersonTableModel extends AbstractTableModel{
	
//	private List<Person> db;
//	private String[] colNames = {"ID","Name","Occupation","Age Category","Employment Category","US Citizen","Tax ID"};
	
	private List<Item> db;
	private String[] colNames = {"ID","Name","Brand","Size","Price","Quantity","Description"};
	
	public PersonTableModel() {
		
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}


	public void setData(List<Item> db) {
		this.db = db;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		//Person person = db.get(row);
		
		Item item = db.get(row);
		
		switch (col) {
		case 0:
			return item.getId();
		case 1:
			return item.getItemName();
		case 2:
			return item.getItemBrand();
		case 3:
			return item.getItemSize();
		case 4:
			return item.getItemPrice();
		case 5:
			return item.getItemQuantity();
		case 6:
			return item.getItemDescription();
		}
		return null;
	}

}
