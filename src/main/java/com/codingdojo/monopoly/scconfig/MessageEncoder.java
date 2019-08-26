package com.codingdojo.monopoly.scconfig;

import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.DiceMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.UserMessage;

public class MessageEncoder implements Encoder.Text<Message>{

	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(Message message) throws EncodeException {
		String returnString = null;
		if (message instanceof ChatMessage) {
			ChatMessage chatMessage = (ChatMessage) message;
			returnString = Json.createObjectBuilder().add("messageType", chatMessage.getClass().getSimpleName())
					.add("name", chatMessage.getName())
					.add("message", chatMessage.getMessage())
					.build().toString();
		}
		if (message instanceof DiceMessage) {
			DiceMessage diceMessage = (DiceMessage) message;
			returnString = Json.createObjectBuilder().add("messageType", diceMessage.getClass().getSimpleName())
					.add("name", diceMessage.getName())
					.add("dice1", diceMessage.getDice1())
					.add("dice2", diceMessage.getDice2())
					.build().toString();	
		}
		else if (message instanceof UserMessage) {
			
			UserMessage usersMessage = (UserMessage) message;
			returnString = buildJsonUsersData(usersMessage.getUsers(), usersMessage.getClass().getSimpleName() );
		}

		

		return returnString;
	}
	
	private String buildJsonUsersData(Set<String> set , String messageType) {
		Iterator<String>iterator = set.iterator();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		while (iterator.hasNext()) jsonArrayBuilder.add(iterator.next());
	
		return Json.createObjectBuilder().add("messageType", messageType).add("user", jsonArrayBuilder).build().toString();
		
	}

}
