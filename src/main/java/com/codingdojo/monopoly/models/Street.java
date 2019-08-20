package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Street extends Property {
	private String color;
	private int cost;
	private int rent;
	private int rentColorSet;
	private int h1;
	private int h2;
	private int h3;
	private int h4;
	private int hotel;
	private int houseCost;
	private int hotelCost;
	private int mortgage;
	private int unmortgage;

	public Street(String name, int purchaseValue, String color, int rent, int rentColorSet, int h1, int h2, int h3, int h4, int hotel, int houseCost, int hotelCost) {
		super(name, purchaseValue);
		this.color = color;
		this.rent = rent;
		this.rentColorSet = rentColorSet;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.hotel = hotel;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

	public int getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}

	public int getHotelCost() {
		return hotelCost;
	}

	public void setHotelCost(int hotelCost) {
		this.hotelCost = hotelCost;
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
