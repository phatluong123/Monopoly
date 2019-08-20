package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Utility extends Property {
	private Player ownedBy;
	private boolean isMortgaged;
	private int mortgage;
	private int unmortgage;
	
	public Utility(String name, int purchaseValue) {
		this.ownedBy = null;
		this.isMortgaged = false;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
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