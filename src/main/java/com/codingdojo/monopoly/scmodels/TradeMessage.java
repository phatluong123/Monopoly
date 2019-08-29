package com.codingdojo.monopoly.scmodels;

import java.util.ArrayList;

import com.codingdojo.monopoly.models.Property;

public class TradeMessage implements Message {
	private boolean accepted = false;
	private boolean rejected = false;
	private String sender;
	private String recipient;
	private int senderMoney = 0;
	private int recipientMoney = 0;
	private ArrayList<Property> senderProperties = new ArrayList<>();
	private ArrayList<Property> recipientProperties = new ArrayList<>();
	public boolean isAccepted() {
		return accepted;
	}
	public boolean isRejected() {
		return rejected;
	}
	public String getSender() {
		return sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public int getSenderMoney() {
		return senderMoney;
	}
	public int getRecipientMoney() {
		return recipientMoney;
	}
	public ArrayList<Property> getSenderProperties() {
		return senderProperties;
	}
	public ArrayList<Property> getRecipientProperties() {
		return recipientProperties;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	public void setSender(String firstPlayerID) {
		this.sender = firstPlayerID;
	}
	public void setRecipient(String secondPlayerID) {
		this.recipient = secondPlayerID;
	}
	public void setSenderMoney(int p1MoneyOffer) {
		this.senderMoney = p1MoneyOffer;
	}
	public void setRecipientMoney(int p2MoneyOffer) {
		this.recipientMoney = p2MoneyOffer;
	}
	public void setSenderProperties(ArrayList<Property> p1PropertyOffer) {
		this.senderProperties = p1PropertyOffer;
	}
	public void setRecipientProperties(ArrayList<Property> p2PropertyOffer) {
		this.recipientProperties = p2PropertyOffer;
	}
}
