package com.codingdojo.monopoly.scmodels;

public class DiceMessage implements Message{
	private String name;
	private int dice2;
	private int dice1;
	private int finalLocation;
	public int getFinalLocation() {
		return finalLocation;
	}
	public void setFinalLocation(int finalLocation) {
		this.finalLocation = finalLocation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDice2() {
		return dice2;
	}
	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}
	public int getDice1() {
		return dice1;
	}
	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}
	
}
