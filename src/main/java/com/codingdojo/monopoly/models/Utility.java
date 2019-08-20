package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Utility extends Property {
	private int mortgage;
	private int unmortgage;
	
	public Utility(String name, int purchaseValue) {
		super(name, purchaseValue);
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
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