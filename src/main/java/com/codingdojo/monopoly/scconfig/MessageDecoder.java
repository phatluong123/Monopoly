package com.codingdojo.monopoly.scconfig;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.codingdojo.monopoly.models.Property;
import com.codingdojo.monopoly.models.Space;
import com.codingdojo.monopoly.scmodels.ActionMessage;
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.TradeMessage;

public class MessageDecoder implements Decoder.Text<Message>{

	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message decode(String jsonMessage) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
		System.out.println(jsonObject);
		if (jsonObject.containsKey("message")) {
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setMessage(jsonObject.getString("message"));
			return chatMessage;
		} else if (jsonObject.containsKey("action")) {
			ActionMessage actionMessage = new ActionMessage();
			actionMessage.setAction(jsonObject.getString("action"));
			return actionMessage;
		} else if (jsonObject.containsKey("trade")) {
			TradeMessage tradeMessage = new TradeMessage();
			tradeMessage.setAccepted(jsonObject.getBoolean("accepted"));
			tradeMessage.setFirstPlayerID(jsonObject.getString("sender"));
			tradeMessage.setSecondPlayerID(jsonObject.getString("recipient"));
			tradeMessage.setP1MoneyOffer(jsonObject.getInt("sendermoney", 0));
			tradeMessage.setP2MoneyOffer(jsonObject.getInt("recipientmoney", 0));
			ArrayList<String> senderPropertyNames = new ArrayList<String>(Arrays.asList(jsonObject.getString("giveProperties").split(",")));
			ArrayList<String> recipientPropertyNames = new ArrayList<String>(Arrays.asList(jsonObject.getString("wantProperties").split(",")));
			ArrayList<Property> senderProperties = new ArrayList<>();
			ArrayList<Property> recipientProperties = new ArrayList<>();
			for(String s: senderPropertyNames) {
				senderProperties.add((Property) Space.findSpaceByName(s));
			}
			for(String s: recipientPropertyNames) {
				recipientProperties.add((Property) Space.findSpaceByName(s));
			}
			tradeMessage.setP1PropertyOffer(senderProperties);
			tradeMessage.setP2PropertyOffer(recipientProperties);
			return tradeMessage;
		} else {
			return null;
		}
	}
	
	

	@Override
	public boolean willDecode(String jsonMessage) {
		boolean flag = true;
		
		try { Json.createReader(new StringReader(jsonMessage)).readObject() ;}
		catch (Exception e) {flag = false;}
		return flag;
	}
	
}
