package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Railroad extends Property {
	private int rent;
	private int mortgage;
	private int unmortgage;
	
	public Railroad(String name, int purchaseValue, int rent) {
		super(name, purchaseValue);
		this.rent = rent;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	
	public int getMortgage() {
		return mortgage;
	}

	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}

	public int getUnmortgage() {
		return unmortgage;
	}

	public void setUnmortgage(int unmortgage) {
		this.unmortgage = unmortgage;
	}
}
