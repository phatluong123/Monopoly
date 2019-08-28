package com.codingdojo.monopoly.scconfig;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.script.ScriptException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;
import com.codingdojo.monopoly.models.Property;
import com.codingdojo.monopoly.scmodels.ActionMessage;
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.DiceMessage;
import com.codingdojo.monopoly.scmodels.GamestateMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.UserMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Component
@ServerEndpoint(value="/chatServerEndPoint", encoders= {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ChatServerEndPoint {
	
	public static final Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void handleOpen(Session userSession) throws IOException, EncodeException {
		chatroomUsers.add(userSession);	
		
	}
	
	@OnMessage
	public void handleMessage(Message incomingMessage, Session userSession) throws IOException, EncodeException, NoSuchMethodException, ScriptException {
		String username = (String) userSession.getUserProperties().get("username");
		
		// Dice roll for movement
		if (incomingMessage instanceof ActionMessage) {
			ActionMessage incomingAction = (ActionMessage)incomingMessage;
			String action = incomingAction.getAction();
			Player currentPlayer = Game.getCurrentPlayer();
			if (action.equals("roll")/* && (!Player.hasRolled() || Player.getDoubleRolls() > 0)*/) {
				DiceMessage diceoutgoingMessage = new DiceMessage();
				currentPlayer.movePlayer();
				int[] dice = Game.getLastDiceRoll();
				Integer dice1 = dice[0];
				Integer dice2 = dice[1];
				System.out.println(currentPlayer.getCurrentLocation());
				Game.doStuff(currentPlayer);
				diceoutgoingMessage.setName(username)
						.setDice1(dice1)
						.setDice2(dice2)
						.setFinalLocation(currentPlayer.getCurrentLocation());
				//userSession.getBasicRemote().sendObject(diceoutgoingMessage);
				Iterator<Session> iterator = chatroomUsers.iterator();
				
				while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(diceoutgoingMessage);
			} 
			
			// Jail options
			else if (action.equals("jailroll")){
				if(!currentPlayer.rollToGetOutOfJail()) {
					currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail() + 1);
					if(currentPlayer.getTurnsInJail() > 2) {
						currentPlayer.payFine();
					} else {
						Game.nextPlayer();
					}
				}
			} else if (action.equals("jailfine")) {
				currentPlayer.payFine();
			} else if (action.equals("jailcard")) {
				currentPlayer.playGetOutOfJailCard();
			}
			
			// Buy property
			else if (action.startsWith("buy")) {
				if (!Game.isSpaceOwned(currentPlayer.getCurrentLocation())) {
					Property prop = (Property)Game.getBoard()[currentPlayer.getCurrentLocation()];
					if(prop.getPurchaseValue() <= currentPlayer.getMoney()) {
						currentPlayer.buyProperty(prop);
					}
				}
			} 
			
			// End turn
			else if (action.startsWith("end")) {
				if(currentPlayer.getDebt() > currentPlayer.getMoney()) {
					Game.goBankrupt(currentPlayer);
				}
				Game.nextPlayer();
			}
			
			//Generate gamestate json
			GamestateMessage gamestateMessage = generateGamestateMessage();
			Iterator<Session> iterator = chatroomUsers.iterator();
			while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(gamestateMessage);
			
		}
		if (incomingMessage instanceof ChatMessage) {
			
			ChatMessage incomingChatMessage = (ChatMessage) incomingMessage;
			System.out.println(incomingChatMessage.getMessage());
			
			if (username == null) {
				ChatMessage outgoingChatMessage = new ChatMessage();
				userSession.getUserProperties().put("username", incomingChatMessage.getMessage());
				outgoingChatMessage.setName(incomingChatMessage.getMessage());
				outgoingChatMessage.setMessage(" has logged in");
				userSession.getBasicRemote().sendObject(outgoingChatMessage);
			}
			else {
				ChatMessage outgoingChatMessage = new ChatMessage();
				outgoingChatMessage.setName(username);
				outgoingChatMessage.setMessage(incomingChatMessage.getMessage());
				Iterator<Session> iterator = chatroomUsers.iterator();
				
				while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(outgoingChatMessage);
			}
			Iterator<Session> iterator = chatroomUsers.iterator();
			while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(new UserMessage(getIds()));
		}
	}
	
	
	@OnClose
	public void handleClose(Session userSession) throws IOException, EncodeException {
		chatroomUsers.remove(userSession);
		Iterator<Session> iterator = chatroomUsers.iterator();
		while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(new UserMessage(getIds()));
	}
	
	public static Set<String> getIds(){
		HashSet<String> returnSet = new HashSet<String>();
		Iterator<Session> iterator = chatroomUsers.iterator();
		while (iterator.hasNext()) returnSet.add(iterator.next().getUserProperties().get("username").toString());

		
		return returnSet;
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("error");
		System.out.println(throwable.getClass().toString());
		System.out.println(throwable.getMessage());
		throwable.printStackTrace();
	}
	
	private GamestateMessage generateGamestateMessage() {
		GamestateMessage g = new GamestateMessage();
		Gson gson = new GsonBuilder()
				.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
				.serializeNulls()
				.create();
		g.setGamestate(gson.toJson(new Game()));
		return g;
		
	}
	
}