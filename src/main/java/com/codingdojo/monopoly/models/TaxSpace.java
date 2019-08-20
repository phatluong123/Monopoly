package com.codingdojo.monopoly.models;

public class TaxSpace extends Space {
	private int amount;

	public TaxSpace() {
		super();
	}

	public TaxSpace(String name, int amount) {
		super(name);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
