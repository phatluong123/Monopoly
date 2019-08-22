package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Utility extends Property {
	
	public Utility(String name) {
		super(name, 150, "utility");
	}

	@Override
	public int getRentCost() {
		int roll = Game.rollDie() + Game.rollDie();
		boolean ownsAll = Game.ownsFullSet(this.getOwnedBy(), this.getSet());
		int multiple = 4;
		if(ownsAll) multiple = 10;
		return roll * multiple;
	}
}