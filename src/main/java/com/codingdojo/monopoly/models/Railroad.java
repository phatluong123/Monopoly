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
		private Player ownedBy = null;
		private boolean isMortgaged = false;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	
	
	public Player getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}
	
	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
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
