package com.fishingapp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fishingapp.model.Item;

public class InventoryTableModel extends AbstractTableModel{
	
	private List<Item> dbInv;
	private String[] colNames = {"ID","Name","Brand","Size","Price","Quantity","Description","On Sale", "In Stock"};
	
	public InventoryTableModel() {
		
	}
	
	public void setData(List<Item> dbInv) {
		this.dbInv = dbInv;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return dbInv.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
	
		Item item = dbInv.get(row);
		
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
		case 7:
			if(item.isItemOnSale()) {return "Item On Sale";}else {return "Not On Sale";}
			//return item.isItemOnSale();
		case 8:
			//System.out.println(item.isItemOnStock());
			if(item.isItemOnStock()) {return "Item In Stock";}else {return "Out Of Stock";}
			//return item.isItemOnStock();
		}
		return null;
	}

	

}
