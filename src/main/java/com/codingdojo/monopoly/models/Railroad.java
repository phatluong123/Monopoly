package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Railroad extends Property {
	private int rent;
	
	public Railroad(String name) {
		super(name, 200, "railroad");
		this.rent = 25;
	}

	public int getRent() {
		return rent;
	}
	
	public int getRent(int numOwned) {
		return rent * (int)Math.pow(2, numOwned - 1);
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	@Override
	public int getRentCost() {
		return (int) (rent * (Math.pow(2.0, this.getOwnedBy().getNumOfSetOwned(this.getSet()) - 1)));
	}
}
