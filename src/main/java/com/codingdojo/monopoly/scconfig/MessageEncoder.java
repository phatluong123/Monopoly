package com.codingdojo.monopoly.scconfig;

import java.io.Console;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.codingdojo.monopoly.models.Property;
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.DiceMessage;
import com.codingdojo.monopoly.scmodels.GamestateMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.ResetMessage;
import com.codingdojo.monopoly.scmodels.TradeMessage;
import com.codingdojo.monopoly.scmodels.TurnMessage;
import com.codingdojo.monopoly.scmodels.UserMessage;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
					.add("finalLocation", diceMessage.getFinalLocation())
					.build().toString();	
		}
		else if(message instanceof GamestateMessage) {
			GamestateMessage gamestateMessage = (GamestateMessage) message;
			returnString = Json.createObjectBuilder().add("messageType", gamestateMessage.getClass().getSimpleName())
					.add("gamestate", gamestateMessage.getGamestate())
					.build().toString();
		}
		else if (message instanceof UserMessage) {
			
			UserMessage usersMessage = (UserMessage) message;
			returnString = buildJsonUsersData(usersMessage.getUsers(), usersMessage.getClass().getSimpleName() );
		}
		else if (message instanceof TradeMessage) {
			TradeMessage tradeMessage = (TradeMessage) message;

			Gson gson = new GsonBuilder()
					.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
					.serializeNulls()
					.create();
			returnString = Json.createObjectBuilder().add("messageType", tradeMessage.getClass().getSimpleName())
				.add("trade", gson.toJson(tradeMessage))
				.build().toString();
			
			
			
//			returnString = Json.createObjectBuilder().add("messageType", tradeMessage.getClass().getSimpleName())
//					.add("accepted", tradeMessage.isAccepted())
//					.add("rejected", tradeMessage.isRejected())
//					.add("sender", tradeMessage.getFirstPlayerID())
//					.add("recipient", tradeMessage.getSecondPlayerID())
//					.add("sendermoney", tradeMessage.getP1MoneyOffer())
//					.add("recipientmoney", tradeMessage.getP2MoneyOffer())
//					.add("senderproperties", tradeMessage.getP1PropertyOffer().toString())
//					.add("recipientproperties", tradeMessage.getP2PropertyOffer().toString())
//					.build().toString();
		}
		else if (message instanceof TurnMessage) {
			TurnMessage turnMessage = (TurnMessage) message;
			returnString = Json.createObjectBuilder().add("messageType", turnMessage.getClass().getSimpleName())
					.add("myTurn", turnMessage.isMyTurn())
					.build().toString();
		}
		else if (message instanceof ResetMessage) {
			ResetMessage resetMessage = (ResetMessage) message;
			returnString = Json.createObjectBuilder().add("messageType", resetMessage.getClass().getSimpleName())
					.add("reset", resetMessage.getMessage())
					.add("type", resetMessage.getType())
					.build().toString();
		}
		System.out.println("out message "+returnString);
		return returnString;
	}
	
	private String buildJsonUsersData(Set<String> set , String messageType) {
		Iterator<String>iterator = set.iterator();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		while (iterator.hasNext()) jsonArrayBuilder.add(iterator.next());
	
		return Json.createObjectBuilder().add("messageType", messageType).add("user", jsonArrayBuilder).build().toString();
		
	}

}
