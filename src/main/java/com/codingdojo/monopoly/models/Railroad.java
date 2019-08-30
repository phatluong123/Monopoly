package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Railroad extends Property {
	/**
	 * Basic rent for when a single Railroad is owned. When more railroads are owned, rent is calculated
	 * as rent times 2 to the power of the number of railroads owned minus one.
	 */
	private int rent = 25;
	
	public Railroad() {
		
	}
	
	public Railroad(String name) {
		super(name, 200, "railroad");
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
		return (int) (rent * (Math.pow(2.0, this.getOwner().getNumOfSetOwned(this.getSet()) - 1)));
	}
	
	public int chanceRentCost() {
		return (int) (rent * 2 * (Math.pow(2.0, this.getOwner().getNumOfSetOwned(this.getSet()) - 1)));
	}
}
