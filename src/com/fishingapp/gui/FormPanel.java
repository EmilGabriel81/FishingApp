package com.fishingapp.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.fishingapp.constants.Constants;

public class FormPanel extends JPanel {

	private JButton okBtn;
	private FormListener formListener;
	private JButton clearBtn;

////////////////////////////////////
	private JLabel itemNameLabel;
	private JLabel itemBrandLabel;
	private JLabel itemSizeLabel;
	private JLabel itemPriceLabel;
	private JLabel itemQuantityLabel;
	private JLabel itemDescriptionLabel;

	private JTextField itemNameField;
	private JTextField itemBrandField;
	private JTextField itemSizeField;
	private JTextField itemPriceField;
	private JTextField itemQuantityField;
	private JTextField itemDescriptionField;

	public FormPanel() {

		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);


		itemNameLabel = new JLabel("Name: ");
		itemNameLabel.setFont(Constants.mvBoli2);

		itemBrandLabel = new JLabel("Brand: ");
		itemBrandLabel.setFont(Constants.mvBoli2);

		itemSizeLabel = new JLabel("Size: ");
		itemSizeLabel.setFont(Constants.mvBoli2);

		itemPriceLabel = new JLabel("Price: ");
		itemPriceLabel.setFont(Constants.mvBoli2);

		itemQuantityLabel = new JLabel("Quantity: ");
		itemQuantityLabel.setFont(Constants.mvBoli2);

		itemDescriptionLabel = new JLabel("Description: ");
		itemDescriptionLabel.setFont(Constants.mvBoli2);

		itemNameField = new JTextField(10);
		itemBrandField = new JTextField(10);
		itemSizeField = new JTextField(10);
		itemPriceField = new JTextField(10);
		itemQuantityField = new JTextField(10);
		itemDescriptionField = new JTextField(10);


		// Action Listener
		okBtn = new JButton("Add");
		okBtn.setFont(Constants.mvBoli2);
		okBtn.setFocusable(false);
		clearBtn = new JButton("Clear");
		clearBtn.setFont(Constants.mvBoli2);
		clearBtn.setFocusable(false);

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemNameField.getText().isEmpty() || itemBrandField.getText().isEmpty()
						|| itemSizeField.getText().isEmpty() || itemPriceField.getText().isEmpty()
						|| itemQuantityField.getText().isEmpty() || itemDescriptionField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No Blank Fields Allowed");
				} else {
					String name = itemNameField.getText();
					String brand = itemBrandField.getText();
					Double size = Double.parseDouble(itemSizeField.getText());
					Double price = Double.parseDouble(itemPriceField.getText());
					int quantity = Integer.parseInt(itemQuantityField.getText());
					String description = itemDescriptionField.getText();
					FormEvent ev = new FormEvent(this, name, brand, size, price, quantity, description);

					if (formListener != null) {
						formListener.formEventOccured(ev);
					}
				}
			}
		});

		clearBtn.addActionListener(e -> {
			itemNameField.setText("");
			itemBrandField.setText("");
			itemSizeField.setText("");
			itemPriceField.setText("");
			itemQuantityField.setText("");
			itemDescriptionField.setText("");
		});

		// The Borders
		Border innerBorder = BorderFactory.createTitledBorder("Create Inventory");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder , innerBorder));

		layoutComponents();

	}

	public void layoutComponents() {
		setBackground(Constants.maya);
		// TheLayout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		////// First Row///// Name
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		// Name Label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(itemNameLabel, gc);
		// Name Field
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(itemNameField, gc);
		//////////// Next Row///////////////// Brand
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		// Brand Label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		// add(occupationLabel , gc);
		add(itemBrandLabel, gc);
		// Brand Field
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(itemBrandField, gc);
		///////////// Next Row//////////////////// Size
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(itemSizeLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(itemSizeField, gc);
		///////// Next Row /////// Price
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(itemPriceLabel, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(itemPriceField, gc);
		///////// Next Row /////// Quantity
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(itemQuantityLabel, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(itemQuantityField, gc);
		///////// Next Row /////// Description
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(itemDescriptionLabel, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(itemDescriptionField, gc);
		///////////// Next Row////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;// was 1.0
		// Button
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, -30, 0, 5);
		add(okBtn, gc);
		// clear button
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, -115);
		add(clearBtn, gc);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;

	}

}
