package com.codingdojo.monopoly.scmodels;

import java.util.ArrayList;

import com.codingdojo.monopoly.models.Property;

public class TradeMessage implements Message {
	private boolean accepted = false;
	private boolean rejected = false;
	private String firstPlayerID;
	private String secondPlayerID;
	private int p1MoneyOffer = 0;
	private int p2MoneyOffer = 0;
	private ArrayList<Property> p1PropertyOffer = new ArrayList<>();
	private ArrayList<Property> p2PropertyOffer = new ArrayList<>();
	public boolean isAccepted() {
		return accepted;
	}
	public boolean isRejected() {
		return rejected;
	}
	public String getFirstPlayerID() {
		return firstPlayerID;
	}
	public String getSecondPlayerID() {
		return secondPlayerID;
	}
	public int getP1MoneyOffer() {
		return p1MoneyOffer;
	}
	public int getP2MoneyOffer() {
		return p2MoneyOffer;
	}
	public ArrayList<Property> getP1PropertyOffer() {
		return p1PropertyOffer;
	}
	public ArrayList<Property> getP2PropertyOffer() {
		return p2PropertyOffer;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	public void setFirstPlayerID(String firstPlayerID) {
		this.firstPlayerID = firstPlayerID;
	}
	public void setSecondPlayerID(String secondPlayerID) {
		this.secondPlayerID = secondPlayerID;
	}
	public void setP1MoneyOffer(int p1MoneyOffer) {
		this.p1MoneyOffer = p1MoneyOffer;
	}
	public void setP2MoneyOffer(int p2MoneyOffer) {
		this.p2MoneyOffer = p2MoneyOffer;
	}
	public void setP1PropertyOffer(ArrayList<Property> p1PropertyOffer) {
		this.p1PropertyOffer = p1PropertyOffer;
	}
	public void setP2PropertyOffer(ArrayList<Property> p2PropertyOffer) {
		this.p2PropertyOffer = p2PropertyOffer;
	}
}
