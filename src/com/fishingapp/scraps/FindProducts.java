package com.fishingapp.scraps;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class FindProducts extends JFrame{
	
	public FindProducts() {
		setSize(835, 667);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLocation(400,102);
		panel.add(panel_2);
		panel_2.setSize(400,520);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLocation(0, 102);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setSize(300,518);
	}

}
