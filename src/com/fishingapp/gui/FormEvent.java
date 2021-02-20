package com.fishingapp.gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private String itemName;
	private String itemBrand;
	private double itemSize;
	private double itemPrice;
	private int itemQuantity;
	private String itemDescription;
	private boolean itemOnSale;
	private boolean itemOnStock;

	public FormEvent(Object source) {
		super(source);

	}

	public FormEvent(Object source, String itemName, String itemBrand, double itemSize, double itemPrice, int itemQuantity,
			String itemDescription) {
		super(source);
		
		this.itemName = itemName;
		this.itemBrand = itemBrand;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemDescription = itemDescription;
		this.itemOnSale = false;
		this.itemOnStock = true;

	}

	public String getItemName() {
		return itemName;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public double getItemSize() {
		return itemSize;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public boolean isItemOnSale() {
		return itemOnSale;
	}

	public boolean isItemOnStock() {
		return itemOnStock;
	}

	
	
	

}
