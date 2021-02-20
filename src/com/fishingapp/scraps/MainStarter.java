package com.fishingapp.scraps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.fishingapp.constants.Constants;
import com.fishingapp.controller.Controller;
import com.fishingapp.gui.MainFrame;
import com.shopextension.view.MainShop;

public class MainStarter extends JFrame {

	private JButton addItemsBtn;
	private JButton loadInventoryBtn;
	private JButton quitBtn;
	private JButton findItemBtn;
	private JButton mngReportBtn;
	private JButton viewItemsBtn;
	private JButton orderItemsBtn;
	private JButton statsBtn;
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JLabel titleLbl;
	
	private MainFrame firstFrame;
	private Controller controller;
	
	
	
	public MainStarter(MainFrame firstFrame) {

		super("Fishing Store");
		this.firstFrame = firstFrame;
		this.controller = firstFrame.getController();
		initComponents();
		initLayout();
	}

	private void initComponents() {
	
		
        //The Panels
		mainPanel = new JPanel();
		titlePanel = new JPanel();
        //The Add Item Button	
		addItemsBtn = new JButton("Main Store");
		addItemsBtn.setFont(Constants.mvBoli2);
		addItemsBtn.addActionListener(e ->{ firstFrame.setVisible(true); });
		addItemsBtn.setFocusable(false);
		// Load Inventory
		loadInventoryBtn = new JButton("Load Inventory");
		loadInventoryBtn.setFont(Constants.mvBoli2);
		loadInventoryBtn.addActionListener(e ->{ firstFrame.chooseFile(); });
		loadInventoryBtn.setFocusable(false);
		// Find Items
		findItemBtn = new JButton("Find Item");
		findItemBtn.setFont(Constants.mvBoli2);
		findItemBtn.addActionListener(e ->{ firstFrame.findItems();});
		findItemBtn.setFocusable(false);
		// Management Report
		mngReportBtn = new JButton("Management Report");
		mngReportBtn.addActionListener(e ->{
			JOptionPane.showMessageDialog(this, "Work In Progress"," Under Construction :)", JOptionPane.INFORMATION_MESSAGE);
		});
		mngReportBtn.setFont(Constants.mvBoli2);
		
		mngReportBtn.setFocusable(false);
		// View All Items
		viewItemsBtn = new JButton("View Inventory");
		viewItemsBtn.setFont(Constants.mvBoli2);
		viewItemsBtn.addActionListener(e -> {firstFrame.viewInventory();});
		viewItemsBtn.setFocusable(false);
		//Order Items
		orderItemsBtn = new JButton("Order Items");
		orderItemsBtn.setFont(Constants.mvBoli2);
		orderItemsBtn.addActionListener(e ->{firstFrame.showOrderMenu();});
		orderItemsBtn.setFocusable(false);
		// Stats Button 
		statsBtn = new JButton("History");
		statsBtn.setFont(Constants.mvBoli2);
		statsBtn.addActionListener(e ->{
			firstFrame.showTransactions();
			});
		statsBtn.setFocusable(false);
		//Quit Button
		quitBtn = new JButton("Quit");
		quitBtn.setFont(Constants.mvBoli2);
		quitBtn.setFocusable(false);
		quitBtn.addActionListener(e ->{ System.exit(0);});
		
		titleLbl = new JLabel("Fishing App");
		titleLbl.setFont(Constants.mvBoli);
		
	}

	private void initLayout() {

		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(Constants.maya);
		titlePanel.setBackground(Constants.cornFlower);

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		/////
		
	    /////
		/////////////// First Row/////////////
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		mainPanel.add(addItemsBtn, gc);
		gc.gridx++;
		mainPanel.add(loadInventoryBtn, gc);
		
		/////////////// Next Row///////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		mainPanel.add(findItemBtn, gc);
		gc.gridx++;
		mainPanel.add(viewItemsBtn, gc);
		////////////// Next Row///////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		mainPanel.add(orderItemsBtn, gc);
		gc.gridx++;
		mainPanel.add(mngReportBtn, gc);
		////////// Next Row////////
		gc.gridy++;
		gc.gridx = 0;
		mainPanel.add(statsBtn, gc);
		gc.gridx++;
		mainPanel.add(quitBtn, gc);
		add(titlePanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		titlePanel.setSize(600,200);
		titlePanel.add(titleLbl);
		setLocationRelativeTo(null);
		setSize(900, 600);
	}

}
