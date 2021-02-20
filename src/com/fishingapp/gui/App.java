package com.fishingapp.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.fishingapp.scraps.MainStarter;

public class App {
	
	public static void main(String[] args) {

		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainFrame frameTest = new MainFrame();
				//new MainFrame();
//				testFrame.setSize(400,400);
//				testFrame.setVisible(true);
				
				new MainStarter(frameTest).setVisible(true);
			}
		});
	

	}

}
