package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Property extends Space {
	private int purchaseValue;

	public Property(String name, int purchaseValue) {
		super(name);
		this.purchaseValue = purchaseValue;
	}

	public int getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(int purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
}
