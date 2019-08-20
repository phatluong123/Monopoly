package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.HashMap;

//test

public class Player {
	private String name;
	private int money;
	// Save the name of the street they owned
	private ArrayList<String> ownedProperties;
	// Keep track of number of properties owned in each set.
	private HashMap<String, Integer> setsOwned;
	//keep track of current location of player
	private int currentLocation;
	//Check if they are in jail
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
		this.setsOwned = new HashMap<>();
		this.inJail = false;
		this.ownJailCard = true; 
	}
	//Method that a player can do
	public void payOther(int amount) {
		setMoney(getMoney()-amount);
	}
	
	public void earn(int amount) {
		setMoney(getMoney()+amount);
	}
	
    public void move(int step) {
    	if (getCurrentLocation() + step > 39) 
    		earn(200);
    	setCurrentLocation((getCurrentLocation()+step) % 40);
    	setCurrentLocation(getCurrentLocation()+step);
    }

    public void goToJail() {
    	setInJail(true);
    	setCurrentLocation(10);
    }
    
    public void getOutJail() {
    	setInJail(false);
    }
    
    public void addProperty(String name) {
    	getOwnedProperties().add(name);
    }
    
    public void addProperty(Property property) {
    	String set = property.getSet();
    	String name = property.getName();
    	ArrayList<String> properties = this.getOwnedProperties();
    	HashMap<String, Integer> sets = this.getSetsOwned();
    	if(sets.containsKey(set)) {
    		sets.put(set, sets.get(set) + 1);
    	} else {
    		sets.put(set, 1);
    	}
    	properties.add(name);
    	this.setsOwned = sets;
    	this.ownedProperties = properties;
    }
    
    public Integer getNumOfSetOwned(String set) {
    	return this.getSetsOwned().get(set);
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

	public HashMap<String, Integer> getSetsOwned() {
		return setsOwned;
	}
	
	public void setSetsOwned(HashMap<String, Integer> setsOwned) {
		this.setsOwned = setsOwned;
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
