package com.fishingapp.scraps;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fishingapp.constants.Constants;
import com.fishingapp.model.Item;

public class SearchItemsPanel extends JPanel {
	
	//private JPanel searchPanel;
	private JTextArea displayArea;
	
	
	public SearchItemsPanel() {

		//super("Searched Items");
		
		//searchPanel= new JPanel();
		//searchPanel.setBackground(Constants.maya);
		displayArea= new JTextArea(20,20);
		displayArea.setFont(Constants.mvBoli2);
		
		
		//searchPanel.add(displayArea);
		this.add(displayArea);
		//this.add(searchPanel);
		this.setSize(500,400);
		//this.setLocationRelativeTo(null);
		//this.setVisible(true);
		
	}
	
	public void displaySearchedItem(Item itm) {
		displayArea.setText("Items Found : "+"\n"+"*********************************************"+"\n"+
		        " ID: "+itm.getId()+"\n"+" Name: "+itm.getItemName()+"\n"+
                " Brand: "+itm.getItemBrand()+"\n"+" Size: "+itm.getItemSize()+"\n" +
                " Price: "+itm.getItemPrice()+"\n"+" Quantity: "+itm.getItemQuantity()+"\n"+
                " Description: "+itm.getItemDescription()+"\n"+
                "*********************************************");
	}

}
