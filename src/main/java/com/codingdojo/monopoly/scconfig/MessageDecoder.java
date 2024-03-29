package com.codingdojo.monopoly.scconfig;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;
import com.codingdojo.monopoly.models.Property;
import com.codingdojo.monopoly.models.Space;
import com.codingdojo.monopoly.scmodels.ActionMessage;
import com.codingdojo.monopoly.scmodels.BuildMessage;
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.ResetMessage;
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
		} else if (jsonObject.containsKey("reset")) {
			ResetMessage resetMessage = new ResetMessage();
			resetMessage.setMessage(jsonObject.getString("reset"));
			return resetMessage;
		} else if (jsonObject.containsKey("action")) {
			ActionMessage actionMessage = new ActionMessage();
			actionMessage.setAction(jsonObject.getString("action"));
			return actionMessage;
		} else if (jsonObject.containsKey("build")) {
			BuildMessage buildMessage = new BuildMessage();
			buildMessage.setStreet(Space.findSpaceByName(jsonObject.getString("spaceName")));
			buildMessage.setPerform(jsonObject.getString("perform"));
			return buildMessage;
		} else if (jsonObject.containsKey("trade")) {
			TradeMessage tradeMessage = new TradeMessage();
			if(jsonObject.getString("accepted").equals("true")) {
				tradeMessage.setAccepted(true);
			} else {
				tradeMessage.setAccepted(false);
			}
			if(jsonObject.getString("rejected").equals("true")) {
				tradeMessage.setRejected(true);
			} else {
				tradeMessage.setRejected(false);
			}
			tradeMessage.setSender(jsonObject.getString("sender", Game.getCurrentPlayer().getPlayerID()));
			tradeMessage.setRecipient(jsonObject.getString("recipient"));
			tradeMessage.setSenderMoney(jsonObject.getInt("senderMoney"));
			tradeMessage.setRecipientMoney(jsonObject.getInt("recipientMoney"));
			ArrayList<String> senderPropertyNames = new ArrayList<String>(Arrays.asList(jsonObject.getString("senderProperties").split(",")));
			ArrayList<String> recipientPropertyNames = new ArrayList<String>(Arrays.asList(jsonObject.getString("recipientProperties").split(",")));
			ArrayList<Property> senderProperties = new ArrayList<>();
			ArrayList<Property> recipientProperties = new ArrayList<>();
			if(senderPropertyNames.get(0).length() > 0) {
				for(String s: senderPropertyNames) {
					senderProperties.add((Property) Space.findSpaceByName(s));
				}
			}
			if(recipientPropertyNames.get(0).length() > 0) {
				for(String s: recipientPropertyNames) {
					recipientProperties.add((Property) Space.findSpaceByName(s));
				}
			}
			tradeMessage.setSenderProperties(senderProperties);
			tradeMessage.setRecipientProperties(recipientProperties);
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
