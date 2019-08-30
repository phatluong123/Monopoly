package com.codingdojo.monopoly.scmodels;

import com.codingdojo.monopoly.models.Space;
import com.codingdojo.monopoly.models.Street;

public class BuildMessage implements Message {
	private Street street;
	private String action;
	public Street getStreet() {
		return street;
	}
	public String getAction() {
		return action;
	}
	public void setStreet(Space space) {
		this.street = (Street)space;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
