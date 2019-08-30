package com.codingdojo.monopoly.scmodels;

import com.codingdojo.monopoly.models.Space;
import com.codingdojo.monopoly.models.Street;

public class BuildMessage implements Message {
	private Street street;
	private String perform;
	public Street getStreet() {
		return street;
	}
	public String getPerform() {
		return perform;
	}
	public void setStreet(Space space) {
		this.street = (Street)space;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
}
