package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Utility extends Property {
	
	public Utility() {
		
	}
	
	public Utility(String name) {
		super(name, 150, "utility");
	}

	@Override
	public int getRentCost() {
		int roll = Game.rollDie() + Game.rollDie();
		String activity = new StringBuilder(Game.getCurrentPlayer().getName())
				.append(" landed on ")
				.append(this.getName())
				.append(" and rolled ")
				.append(Integer.toString(roll))
				.toString();
		Game.addActivityLogItem(activity);
		boolean ownsAll = Game.ownsFullSet(this.getOwner(), this.getSet());
		int multiple = 4;
		if(ownsAll) multiple = 10;
		return roll * multiple;
	}
	
	public int chanceRentCost() {
		int roll = Game.rollDie() + Game.rollDie();
		int cost = (roll * 10);
		String activity = new StringBuilder(Game.getCurrentPlayer().getName())
				.append(" advanced to ")
				.append(this.getName())
				.append(" and rolled ")
				.append(Integer.toString(roll))
				.append(", paying ")
				.append(this.getOwnedBy())
				.append(" $")
				.append(cost)
				.toString();
		Game.addActivityLogItem(activity);
		return cost;
	}
}