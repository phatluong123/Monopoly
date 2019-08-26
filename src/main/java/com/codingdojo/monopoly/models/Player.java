package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.codingdojo.monopoly.models.cards.GetOutJailFreeCommunity;
import com.codingdojo.monopoly.models.cards.GetOutOfJailFreeChance;
import com.google.gson.annotations.Expose;

//test

public class Player {
	private static int doubleRolls = 0;
	@Expose private String name;
	@Expose private int money = 1500;
	// Save the name of the street they owned
	@Expose private ArrayList<String> ownedProperties = new ArrayList<>();
	// Keep track of number of properties owned in each set.
	private HashMap<String, Integer> setsOwned = new HashMap<>();
	//keep track of current location of player
	@Expose private int currentLocation = 0;
	//Check if they are in jail
	@Expose private boolean inJail = false;
	// if they have a "Get out Jail card"
	@Expose private boolean ownsChanceJailCard = false;
	@Expose private boolean ownsChestJailCard = false;
	private boolean isBankrupt = false;
	//need number of houses metric for a chance card.
	private int numberOfHouses = 0;
	//need number of hotels metric for a chance card.
	private int numberOfHotels = 0;
	
	/**
	 * Construct a new Player, taking `name` as an argument. All other fields set to defaults.
	 * @param name - String - the name the Player should have.
	 */
	public Player(String name) {
		this.name = name;
	}
	/* ******* PLAYER METHODS BELOW ******* */
	
	/**
	 * Deducts a given amount from the Player's money and returns that value
	 * @param amount - int - Amount to be deducted from the calling Player's money
	 * @return the amount deducted as an int
	 */
	public int pay(int amount) {
		setMoney(getMoney()-amount);
		return amount;
	}
	
	/**
	 * Adds a given amount to the calling Player's money value
	 * @param amount - int - amount of money to be added to this.money
	 */
	public void earn(int amount) {
		setMoney(getMoney()+amount);
	}
	
	/**
	 * Sends money from the calling Player instance to the owner
	 * of the property on which the calling Player has landed.
	 */
	public void payRent() {
		Space[] board = Game.getBoard();
		Property property = (Property) board[this.getCurrentLocation()];
		this.sendMoney(property.getOwnedBy(), property.getRentCost());
	}
	
	/**
	 * Sends money from calling Player to recipient in the amount specified.
	 * @param recipient - Player - Player to which the money is to be sent.
	 * @param amount - int - Amount of money which is to be sent.
	 */
	public void sendMoney(Player recipient, int amount) {
		recipient.earn(this.pay(amount));
	}
	
	/**
	 * Moves the player a given number of steps
	 * @param step - int - the distance to move on the board
	 */
    public void move(int step) {
    	if (getCurrentLocation() + step > 39) 
    		earn(200);
    	setCurrentLocation((getCurrentLocation()+step) % 40);
    }
    
    public void movePlayer() {
    	int dice1 = Game.rollDie();
    	int dice2 = Game.rollDie();
    	if(dice1 == dice2) {
    		doubleRolls++;
    	}
    	else {
    		doubleRolls = 0;
    	}
    	if(doubleRolls == 3) {
    		this.setCurrentLocation(10);
    		doubleRolls = 0;
    		this.setInJail(true);
    	}
    	else {
    		this.move(dice1+dice2);
    	}
    }
    
    /**
     * Moves the player to the given location
     * @param location - int - the location on the board to which the player is intended to move
     */
    public void moveTo(int location) {
    	if (getCurrentLocation() > location)
    		earn(200);
    	setCurrentLocation(location);
    }
    
    /**
     * Sends the player to the Jail square, without collecting $200 from the Go space.
     */
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


	/**
	 * Directly sets the player's current location. Consider using `move` or `moveTo` instead.
	 * @param currentLocation - int - the location to move the Player to.
	 */
	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}



	public boolean isInJail() {
		return inJail;
	}



	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * Plays the player's Get Out Of Jail Free card to release the player from jail. If the player has both, plays the Chance card.
	 */
	public void playGetOutOfJailCard() {
		if(this.ownsChanceJailCard) {
			this.ownsChanceJailCard = false;
			this.getOutJail();
			Game.putChanceCard(new GetOutOfJailFreeChance("Get Out of Jail Free"));
		}
		else if (this.ownsChestJailCard) {
			this.ownsChestJailCard = false;
			this.getOutJail();
			Game.putChestCard(new GetOutJailFreeCommunity("Get Out of Jail Free"));
		}
	}
	
	/**
	 * Method to pay the $50 fine to get out of jail, instead of rolling or playing a Get Out Of Jail Free card.
	 */
	public void payFine() {
		if(this.money >= 50) {
			this.pay(50);
			this.getOutJail();
		}
	}
	
	/**
	 * Roll the dice to try to get out of jail. If player rolls a double, release them from jail.
	 */
	public void rollToGetOutOfJail() {
		int[] roll = Game.rollDice();
		if(roll[0] == roll[1]) {
			this.getOutJail();
		}
	}
	
	public static int getDoubleRolls() {
		return doubleRolls;
	}
	public boolean ownsChanceJailCard() {
		return ownsChanceJailCard;
	}
	public boolean ownsChestJailCard() {
		return ownsChestJailCard;
	}
	public void setOwnsChanceJailCard(boolean ownsChanceJailCard) {
		this.ownsChanceJailCard = ownsChanceJailCard;
	}
	public void setOwnsChestJailCard(boolean ownsChestJailCard) {
		this.ownsChestJailCard = ownsChestJailCard;
	}
}
