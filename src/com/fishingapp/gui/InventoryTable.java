package com.fishingapp.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import com.fishingapp.constants.Constants;
import com.fishingapp.model.Item;

public class InventoryTable extends JFrame {
	
	private JTable invTable;
	private JPanel invPanel;
	private InventoryTableModel invTableModel;
	private JButton invRefreshBtn;
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	

	
	
	public InventoryTable(){
		invPanel = new JPanel();
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.cornFlower);
		titleLabel = new JLabel("Inventory");
		titleLabel.setFont(Constants.mvBoli);
		titlePanel.add(titleLabel);
		
		invRefreshBtn = new JButton("Refresh");
		invRefreshBtn.setFocusable(false);
		invRefreshBtn.setFont(Constants.mvBoli2);
		invRefreshBtn.addActionListener(e ->{ this.refresh(); });
		setBackground(Constants.babyBlue);
		invTableModel = new InventoryTableModel();
		invTable = new JTable(invTableModel);
		invPanel.setLayout(new BorderLayout());
		invPanel.add(titlePanel, BorderLayout.NORTH);
		invPanel.add(new JScrollPane(invTable) , BorderLayout.CENTER);
		invPanel.add(invRefreshBtn,BorderLayout.SOUTH);
		//Borders
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		invPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		titlePanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		add(invPanel);
		setSize(1200,600);
		setLocationRelativeTo(null);
		
	}
	
	public void setData(List<Item> db) {
		invTableModel.setData(db);
	}
	
	public void refresh() {
		invTableModel.fireTableDataChanged();
	}

	/**
	work in progress
	public void appendInventoryName() {
		titleLabel.
	}

	**/
}
