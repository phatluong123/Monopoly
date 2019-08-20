package com.codingdojo.monopoly.models;

import java.util.ArrayList;

//test

public class Player {
	private String name;
	private int money;
	// Save the name of the street they owned
	private ArrayList<String> ownedProperties;
	//keep track of current location of player
	private int currentLocation;
	//Check if they are in jain
	private boolean inJail;
	// if they have a "Get out Jail card"
	private boolean ownJailCard;
	
	public Player(String name) {
		this.name = name;
		//Default money a player have when start a game;
		this.money = 25000;
		this.currentLocation=0;
		//When start the game everyone start at the GO.
		this.ownedProperties= new ArrayList<String>();
		this.inJail = false;
		this.ownJailCard = true; 
	}
	//Method that a player can do
	public void payOther(int amount) {
		setMoney(getMoney()-amount);
	}
	
	public void Earn(int amount) {
		setMoney(getMoney()+amount);
	}
	
    public void move(int step) {
    	setCurrentLocation(getCurrentLocation()+step);
    }

    public void goToJail() {
    	setInJail(true);
    	setCurrentLocation(10);
    }
    
    public void getOutJail() {
    	setInJail(false);
    	setCurrentLocation(0);
    }
    
    public void addProperty(String name) {
    	getOwnedProperties().add(name);
    }
    
    
	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}



	public ArrayList<String> getOwnedProperties() {
		return ownedProperties;
	}



	public void setOwnedProperties(ArrayList<String> ownedProperties) {
		this.ownedProperties = ownedProperties;
	}



	public int getCurrentLocation() {
		return currentLocation;
	}



	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}



	public boolean isInJail() {
		return inJail;
	}



	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}



	public boolean isOwnJailCard() {
		return ownJailCard;
	}



	public void setOwnJailCard(boolean ownJailCard) {
		this.ownJailCard = ownJailCard;
	}
	
	

}
