package com.fishingapp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.*;
import javax.swing.border.Border;

import com.fishingapp.constants.Constants;
import com.fishingapp.controller.Controller;
import com.fishingapp.model.Item;
import com.shopextension.view.MainShop;

public class MainFrame extends JFrame {

	private FormPanel formPanel;
	private Controller controller;
	private TablePanel tablePanel;
	private InventoryTable invTable;
	private FindItemsFrame findFrame;
	// private SearchItemsPanel searchPanel;
	private JFileChooser fileChooser;
	private MainShop shopOrders;
	private ReportsFrame reportFrame;

	public MainFrame() {

		super("Fishing App");
		setLayout(new BorderLayout());
		formPanel = new FormPanel();
		setJMenuBar(createMenuBar());
		tablePanel = new TablePanel();
		invTable = new InventoryTable();
		controller = new Controller();
		tablePanel.setData(controller.getItems());// get the List from the controller
		invTable.setData(controller.getInvItems());
		System.out.println("Items in inventory : " + controller.getInvItems().size());
		findFrame = new FindItemsFrame(controller);
		// searchPanel = new SearchItemsPanel();
		fileChooser = new JFileChooser();
		shopOrders = new MainShop(controller);
		reportFrame = new ReportsFrame();

		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				controller.addItem(e);
				tablePanel.refresh();
			}
		});

		findFrame.setSearchListener(new SearchListener() {
			@Override
			public void searchEventOccured(SearchEvent s) {
				searchOccured(s);
			}
		});

		add(tablePanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		setSize(900, 600);
		// pack();
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("icons/free_fish2.png").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setVisible(true);
	}

	// Menu Bar
	private JMenuBar createMenuBar() {
		// Bar
		JMenuBar menuBar = new JMenuBar();
		// Menu
		JMenu fileMenu = new JMenu("File");
		JMenu items = new JMenu("Items");
		//JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenu ordersMenu = new JMenu("Orders");
		// MenuItems
		JMenuItem exportDataItem = new JMenuItem("Export Data");
		JMenuItem importDataItem = new JMenuItem("Import Data");
		JMenuItem chooseFile = new JMenuItem("Load Inventory");
		JMenuItem viewItems = new JMenuItem("View Items");
		JMenuItem viewInventory = new JMenuItem("View Inventory");
		JMenuItem findItems = new JMenuItem("Find Items");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		JMenuItem showFormItem = new JMenuItem("Person Form");
		JMenuItem showOrderMenu = new JMenuItem("Order Menu");
		JMenuItem showTransactions = new JMenuItem("Transactions History");

		// Adding
		fileMenu.add(exportDataItem);
		// fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(chooseFile);
		fileMenu.add(exitItem);

		showMenu.add(showFormItem);
		//windowMenu.add(showMenu);
		//windowMenu.add(prefsItem);
		// Orders
		ordersMenu.add(showOrderMenu);
		ordersMenu.add(showTransactions);
		// Items
		items.add(findItems);
		items.add(viewItems);
		items.addSeparator();
		items.add(viewInventory);

		menuBar.add(fileMenu);
		//menuBar.add(windowMenu);
		menuBar.add(items);
		menuBar.add(ordersMenu);

		// The Borders
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		menuBar.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// ActionListeners on Items
		// Exit button Menu
		exitItem.addActionListener(e -> {
			exitApp();
		});
		// Find Frame visibility
		findItems.addActionListener(e -> {
			findItems();
		});
		// Work in progress
		viewItems.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "View items");
		});
		// Inventory Table
		viewInventory.addActionListener(e -> {
			viewInventory();
		});
		// To be scraped
		importDataItem.addActionListener(e -> {// String fileName = JOptionPane.showInputDialog(MainFrame.this, "Enter
												// the name of the file: ");
			// if(fileName!=null) {
			String file2 = "C:\\Users\\emilg\\eclipse-workspace\\FishingTest\\src\\Inv01.txt";
			controller.loadFromFile(file2);// }

		});
		// ?????????????????????????????????
		exportDataItem.addActionListener(e -> {
			String fileName = JOptionPane.showInputDialog(MainFrame.this, "Enter the name of the file: ");
			controller.saveInventory(fileName);
		});

		chooseFile.addActionListener(e -> {
			chooseFile();
		});

		/**
		 * *** The Cart****
		 */

		showOrderMenu.addActionListener(e -> {
			showOrderMenu();
		});
		
		showTransactions.addActionListener(e -> { showTransactions();});

		return menuBar;
	}

	public void showTransactions() {
		reportFrame.setVisible(true);
		String logText = controller.getTransactionLog().toString();
		if(logText.contentEquals("")) {
			reportFrame.appendText("\n   ------------------------------------------------Waiting for Transactions ------------------------------------------");
		}else {
			reportFrame.appendText(logText);
		}
		
		//System.out.println(logText+"aaaa");
	}

	public void showOrderMenu() {
		if (controller.getInvItems().isEmpty()) {
			JOptionPane.showMessageDialog(null, "load Inventory from 'Open File' menu", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
		shopOrders.setVisible(true);
		// new MainShop(this.controller);
	}

	public void viewInventory() {
		if (!controller.getInvItems().isEmpty()) {
			invTable.setVisible(true);
			invTable.refresh();// ????
		} else {
			JOptionPane.showMessageDialog(null, "load Inventory from the Menu", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void searchOccured(SearchEvent s) {
		Item itm;
		String name = s.getSearchName();
		double size = s.getSearchSize();
		itm = controller.findTheItem(name, size);
		// System.out.println("from --> mainFrame"+itm.toString());
		if (itm != null) {
			findFrame.appendResult(itm);
		} else if (controller.getInvItems().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Missing Inventory !", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "No Such Record in the Inventory !", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
		// findFrame.appendResult(itm);
		// searchPanel.displaySearchedItem(itm);
		// searchPanel.setVisible(true);

	}

	public void chooseFile() {
		if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
			try {
				controller.loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());
				// The file is located at C:\Users\emilg\OneDrive\Documents
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void findItems() {
		findFrame.setVisible(true);
	}

	private void exitApp() {
		int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application?",
				"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
		if (action == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

	public Controller getController() {
		return controller;
	}

}
