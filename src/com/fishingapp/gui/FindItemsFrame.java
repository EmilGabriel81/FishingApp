package com.fishingapp.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.fishingapp.constants.Constants;
import com.fishingapp.controller.Controller;
import com.fishingapp.model.Item;

public class FindItemsFrame extends JFrame {

	private JPanel titlePanel;
	private JPanel mainPanel;
	private JLabel titleLabel;
	private JLabel nameLbl;
	private JTextField nameTxtField;
	private JLabel sizeLbl;
	private JTextField sizeTxtField;
	private JButton findBtn;
	private JButton clearBtn;
	private Controller controller;
	private JPanel searchPanel;
	private JTextArea displayArea;
	// Search event
	private SearchListener searchListener;

	public FindItemsFrame(Controller controller) {

		this.controller = controller;
		
		initComponents();
		initLayout();
	}

	private void initLayout() {

		this.add(titlePanel, BorderLayout.NORTH);
		

		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 200);
		gc.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(nameLbl, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		mainPanel.add(nameTxtField, gc);
		///////// Next Row /////// Description
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 200);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		mainPanel.add(sizeLbl, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		mainPanel.add(sizeTxtField, gc);
		///////////// Next Row////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;// was 1.0
		// Button
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, -50, 0, 5);
		mainPanel.add(findBtn, gc);
		// clear button
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, -115);
		mainPanel.add(clearBtn, gc);
		
		/**
		 * 
		 */

		this.add(mainPanel, BorderLayout.WEST);
		
		this.add(searchPanel,BorderLayout.CENTER);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private void initComponents() {
		//findItem = new FindItem(controller);
		searchPanel = new JPanel();
		searchPanel.setVisible(true);
		searchPanel.setBackground(Constants.maya);
		displayArea= new JTextArea(20,30);
		displayArea.setFont(new Font("Helvetica", Font.BOLD, 16));
		searchPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
		titlePanel = new JPanel();
		titleLabel = new JLabel("Find Item");
		titleLabel.setFont(Constants.mvBoli);
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Constants.cornFlower);
		mainPanel = new JPanel();
		mainPanel.setBackground(Constants.maya);
		nameLbl = new JLabel("Item name: ");
		nameLbl.setFont(Constants.mvBoli2);
		nameTxtField = new JTextField(10);
		sizeLbl = new JLabel("Item size: ");
		sizeLbl.setFont(Constants.mvBoli2);
		sizeTxtField = new JTextField(10);
		findBtn = new JButton("Find");
		findBtn.setFont(Constants.mvBoli2);
		findBtn.addActionListener(e ->{searchInit();});
		findBtn.setFocusable(false);
		clearBtn = new JButton("Clear");
		clearBtn.setFont(Constants.mvBoli2);
		clearBtn.addActionListener(e ->{ nameTxtField.setText(""); sizeTxtField.setText("");});
		clearBtn.setFocusable(false);
		
		Border innerBorder = BorderFactory.createTitledBorder("Find Item in Inventory");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		searchPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder , innerBorder));

	}

	
	public void searchInit() {
		//if(!nameTxtField.getText().isEmpty() && !sizeTxtField.getText().isEmpty()) {
		String name="";
		Double size=0.0;
		try {
			 name = nameTxtField.getText();
			 size = Double.parseDouble(sizeTxtField.getText());
			 SearchEvent se = new SearchEvent(this, name, size);
				if(searchListener!=null) {
					searchListener.searchEventOccured(se);
				}
				
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong Input Detected \n","Alert",JOptionPane.WARNING_MESSAGE);
			System.out.println(e.getMessage());
		}
			
		
			//}
		//}else {
			
		//}
		
	}

	public void setSearchListener(SearchListener searchListener) {
		this.searchListener = searchListener;
	}

	public void appendResult(Item itm) {
		displayArea.setText("\tItems Found : "+"\n"+"*********************************************"+"\n"+
		        "ID: "+itm.getId()+"\n"+" Name: "+itm.getItemName()+"\n"+
                "Brand: "+itm.getItemBrand()+"\n"+" Size: "+itm.getItemSize()+"\n" +
                "Price: "+itm.getItemPrice()+"\n"+" Quantity: "+itm.getItemQuantity()+"\n"+
                "Description: "+itm.getItemDescription()+"\n"+
                "*********************************************");
	}
}
