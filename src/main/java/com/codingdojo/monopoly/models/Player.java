package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.HashMap;

//test

public class Player {
	private static int doubleRolls = 0;
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
	private boolean isBankrupt;
	//need number of houses metric for a chance card.
	private int numberOfHouses;
	//need number of hotels metric for a chance card.
	private int numberOfHotels;
	
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
		this.numberOfHotels = 0;
		this.numberOfHouses = 0;
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
    }
    
    public void movePlayer(Player p) {
    	int dice1 = Game.rollDie();
    	int dice2 = Game.rollDie();
    	if(dice1 == dice2) {
    		doubleRolls++;
    	}
    	else {
    		doubleRolls = 0;
    	}
    	if(doubleRolls == 3) {
    		p.setCurrentLocation(10);
    		doubleRolls = 0;
    		p.setInJail(true);
    	}
    	else {
    		p.move(dice1+dice2);
    	}
    }
    
    public void moveTo(int location) {
    	if (getCurrentLocation() > location)
    		earn(200);
    	setCurrentLocation(location);
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
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfHouses() {
		return numberOfHouses;
	}
	public void setNumberOfHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
	}
	public int getNumberOfHotels() {
		return numberOfHotels;
	}
	public void setNumberOfHotels(int numberOfHotels) {
		this.numberOfHotels = numberOfHotels;
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

	public boolean isBankrupt() {
		return isBankrupt;
	}
	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}
	public static void setDoubleRolls(int doubleRolls) {
		Player.doubleRolls = doubleRolls;
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
	
	public static int getDoubleRolls() {
		return doubleRolls;
	}
	

}
