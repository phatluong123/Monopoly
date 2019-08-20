package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Property extends Space {
	private int purchaseValue;
	private String set;
	private Player ownedBy = null;
	private boolean isMortgaged = false;
	private int mortgage;
	private int unmortgage;

	public Property(String name, int purchaseValue, String set) {
		super(name);
		this.purchaseValue = purchaseValue;
		this.set = set;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public int getPurchaseValue() {
		return purchaseValue;
	}
	
	public String getSet() {
		return set;
	}

	public Player getOwnedBy() {
		return ownedBy;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public int getMortgage() {
		return mortgage;
	}

	public int getUnmortgage() {
		return unmortgage;
	}

	public void setPurchaseValue(int purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	
	public void setSet(String set) {
		this.set = set;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}

	public void setUnmortgage(int unmortgage) {
		this.unmortgage = unmortgage;
	}
}
