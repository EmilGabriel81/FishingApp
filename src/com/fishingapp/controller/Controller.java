package com.fishingapp.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import com.fishingapp.gui.FormEvent;
import com.fishingapp.gui.MainFrame;
import com.fishingapp.model.Database;
import com.fishingapp.model.Item;
import com.fishingapp.model.ShoppingCartEntry;

public class Controller {
	
	
	Database db = new Database();
	private String infoMessage;
	private StringBuilder transactionLog = new StringBuilder();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	private LocalDateTime now;

	public List<Item> getItems() {
		return db.getItems();
	}
	
	public List<Item> getInvItems() {
		return db.getItemsInventory();
	}

	public void addItem(FormEvent ev) {

		String itemName = ev.getItemName();
		String itemBrand = ev.getItemBrand();
		double itemSize = ev.getItemSize();
		double itemPrice = ev.getItemPrice();
		int itemQuantity = ev.getItemQuantity();
		String itemDescription = ev.getItemDescription();
		boolean itemOnSale = ev.isItemOnSale();
		boolean itemOnStock = ev.isItemOnStock();

		Item item = new Item(itemName, itemBrand, itemSize, itemPrice, itemQuantity, itemDescription);
		db.addItem(item);
	
	}
	/*
	 * Changes
	 */

	public Item findTheItem(String name, double size) {
		Item searchedItem = null;
		//Item searchedItem = new Item();
		//List<Item> myItems = getItems();
		List<Item> myItems = getInvItems();
		for (Item itm : myItems) {
			if (itm.getItemName().equalsIgnoreCase(name) && itm.getItemSize() == size) {
				searchedItem = itm;
				//return itm;
			}
		}
		//System.out.println("searched item is " + searchedItem);
		return searchedItem;
	}

	public void saveInventory(String fileName) {
		List<Item> myItems = getItems();
		int count = myItems.size();
		int toCount = count;
		for (Item itm : myItems) {
			if (saveToFile(itm, fileName)) {
				toCount++;
			}
		}
		if (toCount > count) {
			JOptionPane.showMessageDialog(null, "Records saved in " + fileName + ".txt");
		} else {
			JOptionPane.showMessageDialog(null, "No records available to save", "Alert", JOptionPane.WARNING_MESSAGE);
		}

	}

	public boolean saveToFile(Item item, String fileName) {
		String filePath = "src/" + fileName + ".txt";
		int id = item.getId();
		String itemName = item.getItemName();
		String itemBrand = item.getItemBrand();
		double itemSize = item.getItemSize();
		double itemPrice = item.getItemPrice();
		int itemQuantity = item.getItemQuantity();
		String itemDescription = item.getItemDescription();
		boolean itemOnSale = item.isItemOnSale();
		boolean itemOnStock = item.isItemOnStock();
		boolean result = false;

		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(id + "," + itemName + "," + itemBrand + "," + itemSize + "," + itemPrice + "," + itemQuantity
					+ "," + itemDescription + "," + itemOnSale + "," + itemOnStock);
			result = true;
			pw.flush();
			pw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Records not saved" + "\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			// System.out.println(e.getMessage());
		}
		return result;
	}

	public void loadFromFile(String fileName) {
		//String filePath = "src/" + fileName + ".txt";
		String line = "";
		db.getItemsInventory().clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				fetchData(line);
				//System.out.println(line);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"The system cannot find the file specified", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fetchData(String line) {
		String[] tokens = line.split(",");
		int id = Integer.parseInt(tokens[0]);
		String name = tokens[1];
		String brand = tokens[2];
		double size = Double.parseDouble(tokens[3]);
		double price = Double.parseDouble(tokens[4]);
		int quantity = Integer.parseInt(tokens[5]);
		String description = tokens[6];
		boolean onSale = Boolean.parseBoolean(tokens[7]);
		boolean onStock = Boolean.parseBoolean(tokens[8]);
		
		//System.out.println(id+"  "+name+"  "+brand+"   "+size+"    "+price+ "   "+quantity+"   "+description+"     "+onSale+"   "+onStock+"\n");
		Item item = new Item();
		item.setId(id);
		item.setItemName(name);
		item.setItemBrand(brand);
		item.setItemSize(size);
		item.setItemPrice(price);
		item.setItemQuantity(quantity);
		item.setItemDescription(description);
		item.setItemOnSale(onSale);
		item.setItemOnStock(onStock);
		db.addInventory(item);

	}
	
	
	/**
	 *********************************************** Cart Content Controller***********************************************************
	 * 
	 * 
	 */

	public void addToCart(ShoppingCartEntry entry) {
		db.addEntries(entry);
		System.out.println("Entry added, total items in cart:" + db.getCartItems().size());

	}

	public List<ShoppingCartEntry> getCartContent() {
		return db.getCartItems();
	}

	public double getTotalPrice() {
		double total = 0;
		for (ShoppingCartEntry entry : getCartContent()) {
			total += entry.getTotalPrice();
		}
		return total;
	}

	public String getCartDetails() {
		StringBuilder cartItems = new StringBuilder();
		cartItems.append(
				" ------------------------------------------------------------------------------------------------------- \n");
		for (ShoppingCartEntry en : getCartContent()) {
			cartItems.append(en.getName() + " " + en.getProdDescr() + " ,Size: " + en.getProdSize()
					+ "                 " + en.getQuantity() + " X " + en.getPrice() + " $             Total : "
					+ en.getTotalPrice() + " $ \n");
		}
		cartItems.append(
				" ------------------------------------------------------------------------------------------------------- \n");
		now = LocalDateTime.now();
		transactionLog.append("\n "+dtf.format(now)+cartItems);
		String build = cartItems.toString();
		return build;
	}

	public int cancelOrder() {
		if (!this.getCartContent().isEmpty()) {
			adjustStock();
			now = LocalDateTime.now();
			transactionLog.append("\n "+dtf.format(now)+" "+"  -->  Tranzaction canceled ...\n");
			getCartContent().clear();
			return 0;
		}
		return -1;
	}

	public int submitOrder() {
		infoMessage = "";
		if (!getCartContent().isEmpty()) {
			for (Item itm : getInvItems()) {
				for (ShoppingCartEntry entry : getCartContent()) {
					if (itm.getItemName().equalsIgnoreCase(entry.getName())
							&& itm.getItemSize() == entry.getProdSize()) {
						// System.out.println("Item : "+itm.getItemName()+" ->"+itm.getItemQuantity()+
						// " Entry : "+entry.getName()+" "+entry.getQuantity());
						// itm.setItemQuantity(itm.getItemQuantity()-entry.getQuantity());
						// System.out.println("Items left in store : "+itm.getItemName()+"
						// "+itm.getItemQuantity());
						setInfoMessage(infoMessage
								.concat(entry.getName() + " " + entry.getProdDescr() + " " + entry.getQuantity()
										+ " pcs" + "  -----------------    Total " + entry.getTotalPrice() + " $ \n"));
					}
				}
			}
			setInfoMessage(getInfoMessage() + " \n" + "Total price : " + getTotalPrice() + " $ \n");
			now = LocalDateTime.now();
			transactionLog.append("\n"+dtf.format(now)+"\n "+getInfoMessage());
			getCartContent().clear();
			return 0;
		}
		return -1;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	public int checkStock(int amountRequired, int amountAvailable) {

		if (amountRequired <= amountAvailable) {
			return amountRequired;
		}
		return amountAvailable;
	}

	public void adjustStock() {
		for (ShoppingCartEntry entry : getCartContent()) {
			for (Item item : getInvItems()) {
				if (item.getItemName().equalsIgnoreCase(entry.getName()) && item.getItemSize() == entry.getProdSize()) {
					item.setItemQuantity(item.getItemQuantity() + entry.getQuantity());
					System.out.println("adjust stock --> items " + item.getItemQuantity());
				}
			}
		}
	}

	public StringBuilder getTransactionLog() {
		return transactionLog;
	}

	

}
