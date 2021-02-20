package com.fishingapp.gui;

import java.util.EventObject;

public class SearchEvent extends EventObject {

	private String searchName;
	private double searchSize;
	
	public SearchEvent(Object source) {
		super(source);
		
	}
	
	public SearchEvent(Object source, String searchName, double searchSize) {
		super(source);
		this.searchName = searchName;
		this.searchSize = searchSize;
	}

	public String getSearchName() {
		return searchName;
	}

	public double getSearchSize() {
		return searchSize;
	}

	
}
