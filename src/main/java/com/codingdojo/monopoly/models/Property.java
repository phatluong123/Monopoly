package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Property extends Space {
	private int purchaseValue;
	private Player ownedBy = null;
	private boolean isMortgaged = false;

	public Property(String name, int purchaseValue) {
		super(name);
		this.purchaseValue = purchaseValue;
	}

	public int getPurchaseValue() {
		return purchaseValue;
	}

	public Player getOwnedBy() {
		return ownedBy;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setPurchaseValue(int purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}
}
