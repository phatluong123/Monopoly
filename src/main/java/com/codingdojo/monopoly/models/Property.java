package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Property extends Space {
	private int purchaseValue;
	private String set;
	// ownedBy will be stored as the player's UUID (Player.playerID)
	private String ownedBy = null;
	private boolean isMortgaged = false;
	private int mortgage;
	private int unmortgage;
	
	public Property() {
		
	}

	public Property(String name, int purchaseValue, String set) {
		super(name);
		this.purchaseValue = purchaseValue;
		this.set = set;
		this.mortgage = this.getPurchaseValue() / 2;
		this.unmortgage = mortgage + (mortgage / 10);
	}

	public int getMortgage() {
		return mortgage;
	}
	
	public String getOwnedBy() {
		return ownedBy;
	}

	/**
	 * Finds the owner of a given property as a Player instance.
	 * @return Player if the owner exists in the game; else null
	 */
	public Player getOwner() {
		for(Player p: Game.getPlayers()) {
			if (p.getPlayerID().equals(this.getOwnedBy())) {
				return p;
			}
		}
		return null;
	}
	
	public int getPurchaseValue() {
		return purchaseValue;
	}
	
	/**
	 * Returns the cost of rent for a given property, based upon
	 * ownership and other conditions for each type of property.
	 * @return Returns int of the rental cost
	 */
	public abstract int getRentCost();

	public String getSet() {
		return set;
	}

	public int getUnmortgage() {
		return unmortgage;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	/**
	 * Mortgage a property
	 * <p>
	 * Sets a property to be mortgaged, and gives money to
	 * the owner of the property based on its mortgage value
	 * </p>
	 * 
	 * Overridden by the Street class
	 */
	public void mortgageProperty() {
		this.getOwner().earn(this.mortgage);
		this.isMortgaged = true;
	}
	
	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}

	public void setOwner(Player player) {
		this.setOwnedBy(player.getPlayerID());
	}

	public void setPurchaseValue(int purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	
	public void setSet(String set) {
		this.set = set;
	}
	
	public void setUnmortgage(int unmortgage) {
		this.unmortgage = unmortgage;
	}
	
	public void unmortgageProperty() {
		this.getOwner().pay(this.unmortgage);
		this.isMortgaged = false;
	}
	
	public boolean isOwned() {
		if(this.getOwnedBy() == null) {
			return false;
		} else {
			return true;
		}
	}
}
