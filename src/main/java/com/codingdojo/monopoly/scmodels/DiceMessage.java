package com.codingdojo.monopoly.scmodels;

public class DiceMessage implements Message{
	private String name;
	private int dice2;
	private int dice1;
	private int finalLocation;
	public int getFinalLocation() {
		return finalLocation;
	}
	public DiceMessage setFinalLocation(int finalLocation) {
		this.finalLocation = finalLocation;
		return this;
	}
	public String getName() {
		return name;
	}
	public DiceMessage setName(String name) {
		this.name = name;
		return this;
	}
	public int getDice2() {
		return dice2;
	}
	public DiceMessage setDice2(int dice2) {
		this.dice2 = dice2;
		return this;
	}
	public int getDice1() {
		return dice1;
	}
	public DiceMessage setDice1(int dice1) {
		this.dice1 = dice1;
		return this;
	}
	
}
