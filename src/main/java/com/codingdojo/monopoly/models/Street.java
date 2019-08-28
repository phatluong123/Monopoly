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

	
	/**
	 * Constructs a new Street for placement into the game's board array
	 * @param name - String - Declare the name of the street e.g. Boardwalk
	 * @param purchaseValue - int - Declare cost to purchase a property e.g. 400
	 * @param color - String - Declare the color of the set e.g. blue
	 * @param rent - int - Declare basic rent value e.g. 50. Rent with full color set is twice this value
	 * @param h1 - int - Declare rent value with one house (numHouses == 1)
	 * @param h2 - int - Declare rent value with two houses (numHouses == 2)
	 * @param h3 - int - Declare rent value with three houses (numHouses == 3)
	 * @param h4 - int - Declare rent value with four houses (numHouses == 4)
	 * @param hotel - int - Declare rent value with a hotel (numHouses == 5)
	 * @param housingCost - int - Declare cost to build a new structure
	 */
	public Street() {
		
	}
	
	public Street(String name, int purchaseValue, String color, int rent, int h1, int h2, int h3, int h4, int hotel, int housingCost) {
		super(name, purchaseValue, color);
		this.color = color;
		this.rent = rent;
		this.rentColorSet = rent * 2;
		this.numHouses = 0;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.hotel = hotel;
		this.housingCost = housingCost;
	}

	public String getColor() {
		return color;
	}

	public int getRent() {
		return rent;
	}

	public int getRentColorSet() {
		return rentColorSet;
	}

	public int getNumHouses() {
		return numHouses;
	}

	public int getH1() {
		return h1;
	}

	public int getH2() {
		return h2;
	}

	public int getH3() {
		return h3;
	}

	public int getH4() {
		return h4;
	}

	public int getHotel() {
		return hotel;
	}

	public int getHousingCost() {
		return housingCost;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setRentColorSet(int rentColorSet) {
		this.rentColorSet = rentColorSet;
	}

	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	public void setH1(int h1) {
		this.h1 = h1;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public void setH3(int h3) {
		this.h3 = h3;
	}

	public void setH4(int h4) {
		this.h4 = h4;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public void setHousingCost(int housingCost) {
		this.housingCost = housingCost;
	}
	
	/**
	 * Builds a new structure on the property.
	 * @return true if and only if the purchase was successful,
	 * false if player does not own full set, else if property 
	 * has max numHouses value, else if player has insufficient funds
	 */
	public boolean buyStructure() {
		if(!Game.ownsFullSet(this.getOwner(), this.getSet())) return false;
		if(this.numHouses >= 5) return false;
		if(this.getOwner().getMoney() < this.getHousingCost()) return false;
		this.getOwner().pay(this.getHousingCost());
		this.numHouses++;
		return true;
	}
	
	/**
	 * Sells a structure for half the value of the original purchase
	 * @return true if and only if the sale was successful
	 */
	public boolean sellStructure() {
		if(this.numHouses < 1) return false;
		this.numHouses--;
		this.getOwner().earn(this.getHousingCost()/2);
		return true;
	}
	
	/**
	 * Returns the rent value of a property
	 * based upon ownership of the property,
	 * its associated sets, and the number of houses
	 * thereupon.
	 */
	@Override
	public int getRentCost() {
		if (this.isMortgaged()) return 0;
		switch(this.numHouses) {
		case 1: return h1;
		case 2: return h2;
		case 3: return h3;
		case 4: return h4;
		case 5: return hotel;
		default: 
			if(Game.ownsFullSet(this.getOwner(), this.getSet())) return rentColorSet;
			else return rent;
		}
	}
	
	@Override
	public void mortgageProperty() {
		if (this.numHouses > 0) {
			this.getOwner().earn(this.numHouses * (this.housingCost / 2));
			this.numHouses = 0;
		}
		this.getOwner().earn(this.getMortgage());
		this.setMortgaged(true);
	}
}
