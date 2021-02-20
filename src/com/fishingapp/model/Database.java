package com.fishingapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Database {

	private Connection con;

	private ArrayList<Item> items;
	private ArrayList<Item> itemsInventory;
	
	List<ShoppingCartEntry> cartItems;

	public Database() {
		
		items = new ArrayList<Item>();
		itemsInventory = new ArrayList<Item>();
		
		cartItems = new ArrayList<ShoppingCartEntry>();
		
	}

	public void connect() throws Exception {

		if (con != null)
			return;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		String url = "jdbc:mysql://localhost:3306/swingtest";
		con = DriverManager.getConnection(url, "root", "");

		System.out.println("Connected: " + con);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can`t close connection");
			}
		}
	}

	public void addItem(Item item) {
		items.add(item);
		//Item item2 = new Item("Hook", "Mustad", 3.5, 0.75, 20, "Universal");
		//items.add(item2);
		System.out.println("There are "+items.size()+" elements in the BackEnd");
	}

	public List<Item> getItems() {
		return items;
	}
	
	/*
	 * Changes
	 */
	
	
	public void addInventory(Item item) {
		
		itemsInventory.add(item);
//		for(Item itm : itemsInventory) {
//			System.out.println(itm.toString());
//		}
		//System.out.println("Total Items in inventory : "+itemsInventory.size());
	}

	public List<Item> getItemsInventory() {
		return itemsInventory;
	}
	
	
	/**
	 *  *********************************** Cart Items***********************************
	 *  
	 */
	
	public void addEntries(ShoppingCartEntry entry) {
		cartItems.add(entry);
		
	}
	
	public List<ShoppingCartEntry> getCartItems() {
		return cartItems;
	}
	
}
