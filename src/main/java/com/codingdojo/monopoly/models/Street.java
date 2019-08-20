package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Street extends Property {
	private String color;
	private int rent;
	private int rentColorSet;
	private int numHouses;
	private int h1;
	private int h2;
	private int h3;
	private int h4;
	private int hotel;
	private int housingCost;
	private Player ownedBy;
	private boolean isMortgaged;
	private int mortgage;
	private int unmortgage;

	public Street(String name, int purchaseValue, String color, int rent, int numHouses, int h1, int h2, int h3, int h4, int hotel, int housingCost, boolean isMortgaged, Player ownedBy) {
		super(name, purchaseValue);
		this.color = color;
		this.rent = rent;
		this.rentColorSet = rent * 2;
		this.numHouses = numHouses;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.hotel = hotel;
		this.ownedBy = null;
		this.isMortgaged = false;
		this.housingCost = housingCost;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getRentColorSet() {
		return rentColorSet;
	}

	public void setRentColorSet(int rentColorSet) {
		this.rentColorSet = rentColorSet;
	}
	

	public int getNumHouses() {
		return numHouses;
	}

	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	public int getH1() {
		return h1;
	}

	public void setH1(int h1) {
		this.h1 = h1;
	}

	public int getH2() {
		return h2;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getH3() {
		return h3;
	}

	public void setH3(int h3) {
		this.h3 = h3;
	}

	public int getH4() {
		return h4;
	}

	public void setH4(int h4) {
		this.h4 = h4;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public int getHousingCost() {
		return housingCost;
	}

	public void setHousingCost(int housingCost) {
		this.housingCost = housingCost;
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
