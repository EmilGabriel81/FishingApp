package com.fishingapp.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.fishingapp.constants.Constants;

public class ReportsFrame extends JFrame {
	
	private JPanel titlePanel;
	private JPanel mainPanel;
	private JLabel titleLabel;
	private JTextArea displayArea;
	
	public ReportsFrame() {
	
	titlePanel = new JPanel();
	titlePanel.setBackground(Constants.cornFlower);
	
	titleLabel = new JLabel("Logs / Reports");
	titleLabel.setFont(Constants.mvBoli);
	titlePanel.add(titleLabel);
	
	displayArea= new JTextArea(20,50);
	displayArea.setFont(new Font("Helvetica", Font.BOLD, 16));
	
	mainPanel = new JPanel();
	mainPanel.setBackground(Constants.maya);
	mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
	
	
	this.add(titlePanel, BorderLayout.NORTH);
	this.add(mainPanel, BorderLayout.CENTER);
	//Borders
	Border innerBorder = BorderFactory.createTitledBorder("Transaction History");
	Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	mainPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder , innerBorder));
	
	this.setSize(800,600);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void appendText(String text) {
		clearTextArea();
		displayArea.append(text);
	}
	
	public void clearTextArea() {
		displayArea.setText("");
	}

}
