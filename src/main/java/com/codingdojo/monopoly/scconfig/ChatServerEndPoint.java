package com.codingdojo.monopoly.scconfig;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
import com.codingdojo.monopoly.scmodels.TradeMessage;
import com.codingdojo.monopoly.scmodels.TurnMessage;
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

		Iterator<Session> mapBuilderIterator = chatroomUsers.iterator();
		HashMap<String, Session> socketMap = new HashMap<>();
		while (mapBuilderIterator.hasNext()) {
			Session nextSession = mapBuilderIterator.next();
			socketMap.put((String)nextSession.getUserProperties().get("username"), nextSession);
		}
		// Dice roll for movement
		if (incomingMessage instanceof ActionMessage) {
			ActionMessage incomingAction = (ActionMessage)incomingMessage;
			String action = incomingAction.getAction();
			Player currentPlayer = Game.getCurrentPlayer();
			if(currentPlayer.isInJail()) {
				// Jail options
				if (action.equals("roll")){
					if(!currentPlayer.rollToGetOutOfJail()) {
						currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail() + 1);
						if(currentPlayer.getTurnsInJail() > 2) {
							String activity = currentPlayer.getName().concat(" failed to roll out of jail and paid $50!");
							Game.addActivityLogItem(activity);
							currentPlayer.payFine();
						} else {
							String activity = currentPlayer.getName().concat(" failed to roll out of jail!");
							Game.addActivityLogItem(activity);
							Game.nextPlayer();
						}
					} else {
						String activity = currentPlayer.getName().concat(" successfully rolled out of jail!");
						Game.addActivityLogItem(activity);
					}
				} else if (action.equals("jail-fine")) {
					String activity = currentPlayer.getName().concat(" paid $50 to get out of jail.");
					Game.addActivityLogItem(activity);
					currentPlayer.payFine();
				} else if (action.equals("jail-card")) {
					String activity = currentPlayer.getName().concat(" played a get out of jail free card.");
					Game.addActivityLogItem(activity);
					currentPlayer.playGetOutOfJailCard();
				}
				
			}
			else {
				if (action.equals("roll") && (!Player.hasRolled() || Player.getDoubleRolls() > 0)) {
					DiceMessage diceoutgoingMessage = new DiceMessage();
					currentPlayer.movePlayer();
					int[] dice = Game.getLastDiceRoll();
					Integer dice1 = dice[0];
					Integer dice2 = dice[1];
					String activity = currentPlayer.getName()
							.concat(" rolled ")
							.concat(dice1.toString())
							.concat(" and ")
							.concat(dice2.toString())
							.concat("(")
							.concat(Integer.toString(dice1 + dice2))
							.concat(")");
					Game.addActivityLogItem(activity);
					Game.spaceAction(currentPlayer);
					diceoutgoingMessage.setName(username)
							.setDice1(dice1)
							.setDice2(dice2)
							.setFinalLocation(currentPlayer.getCurrentLocation());
					//userSession.getBasicRemote().sendObject(diceoutgoingMessage);
					Iterator<Session> iterator = chatroomUsers.iterator();
					
					while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(diceoutgoingMessage);
				} 
				
				
				
				// Buy property
				else if (action.startsWith("buy")) {
					if (!Game.isSpaceOwned(currentPlayer.getCurrentLocation())) {
						Property prop = (Property)Game.getBoard()[currentPlayer.getCurrentLocation()];
						if(prop.getPurchaseValue() <= currentPlayer.getMoney() && !prop.isOwned()) {
							String activity = currentPlayer.getName()
									.concat(" bought ")
									.concat(prop.getName())
									.concat(" for $")
									.concat(Integer.toString(prop.getPurchaseValue()));
							Game.addActivityLogItem(activity);
							currentPlayer.buyProperty(prop);
						}
					}
				}
				// End turn
				else if (action.startsWith("end")) {
					currentPlayer.pay(currentPlayer.getDebt());
					currentPlayer.setDebt(0);
					if(currentPlayer.getDebt() > currentPlayer.getMoney()) {
						String activity = currentPlayer.getName().concat(" went bankrupt!");
						Game.addActivityLogItem(activity);
						Game.goBankrupt(currentPlayer);
					}
					currentPlayer.setDebtOwedTo(null);
					String activity = currentPlayer.getName().concat(" ended their turn.");
					Game.addActivityLogItem(activity);
					Game.nextPlayer();
				}
			}
			//Generate gamestate json
			TurnMessage currentPlayerTurnMessage = new TurnMessage();
			TurnMessage otherPlayersTurnMessage = new TurnMessage();
			currentPlayerTurnMessage.setMyTurn(true);
			otherPlayersTurnMessage.setMyTurn(false);
			for(Map.Entry<String, Session> entry : socketMap.entrySet()) {
			    String name = entry.getKey();
			    Session currentSocket = entry.getValue();
			    if(name.equals(Game.getCurrentPlayer().getName())) {
			    	currentSocket.getBasicRemote().sendObject(currentPlayerTurnMessage);
			    } else {
			    	currentSocket.getBasicRemote().sendObject(otherPlayersTurnMessage);
			    }
			}
			InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
			GamestateMessage gamestateMessage = generateGamestateMessage();
			Iterator<Session> iterator = chatroomUsers.iterator();
			while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(gamestateMessage);
			
		} else if (incomingMessage instanceof ChatMessage) {
			
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
		} else if (incomingMessage instanceof TradeMessage) {
			TradeMessage tradeoutgoingMessage = new TradeMessage();
			Player sender = Player.findPlayer(((TradeMessage) incomingMessage).getSender());
			Player recipient = Player.findPlayer(((TradeMessage) incomingMessage).getRecipient());
			ArrayList<Property> offeredProperties = ((TradeMessage) incomingMessage).getSenderProperties();
			ArrayList<Property> requestedProperties = ((TradeMessage) incomingMessage).getRecipientProperties();
			Integer offeredMoney = Math.min(sender.getMoney(), ((TradeMessage) incomingMessage).getSenderMoney());
			Integer requestedMoney = Math.min(recipient.getMoney(), ((TradeMessage) incomingMessage).getRecipientMoney());
			if(offeredProperties.size() > 0) {
				for(Property p: offeredProperties) {
					if(!sender.getOwnedProperties().contains(p)) {
						offeredProperties.remove(p);
					}
				}
			}
			if(requestedProperties.size() > 0) {
				for(Property p: requestedProperties) {
					if(!recipient.getOwnedProperties().contains(p)) {
						offeredProperties.remove(p);
					}
				}
			}
			/**
			 * If the trade offer is accepted, then perform the trade and log it.
			 */
			if(((TradeMessage) incomingMessage).isAccepted()) {
				StringBuilder logStringBuilder = new StringBuilder(sender.getName())
						.append(" and ")
						.append(recipient.getName())
						.append(" completed a trade. ");
				if(offeredProperties.size() > 0) {
					logStringBuilder.append(sender.getName()).append(" traded properties: ");
				}
				for(int i = 0; i < offeredProperties.size(); i++) {
					if(i == offeredProperties.size()-1 && i != 0) {
						logStringBuilder.append(", and ");
					} else if (i > 0){
						logStringBuilder.append(", ");
					}
					Property p = offeredProperties.get(i);
					sender.tradeProperty(p, recipient);
					logStringBuilder.append(p.getName());
				}
				if(requestedProperties.size() > 0) {
					logStringBuilder.append(recipient.getName()).append(" traded properties: ");
				}
				for(int i = 0; i < requestedProperties.size(); i++) {
					if(i == requestedProperties.size()-1 && i != 0) {
						logStringBuilder.append(", and ");
					} else if (i > 0){
						logStringBuilder.append(", ");
					}
					Property p = requestedProperties.get(i);
					recipient.tradeProperty(p, sender);
					logStringBuilder.append(p.getName());
				}
				if(offeredMoney > 0) {
					logStringBuilder.append(sender.getName()).append(" sent $").append(offeredMoney).append(".");
					sender.sendMoney(recipient, offeredMoney);
				}
				if(requestedMoney > 0) {
					logStringBuilder.append(sender.getName()).append(" sent $").append(offeredMoney).append(".");
					recipient.sendMoney(sender, requestedMoney);
				}
				Game.addActivityLogItem(logStringBuilder.toString());
			} 
			/**
			 * If trade is rejected, cease propagation of message and log the failed trade.
			 */
			else if (((TradeMessage) incomingMessage).isRejected()) {
				String activity = new StringBuilder(recipient.getName())
						.append(" rejected a trade from ")
						.append(sender.getName())
						.append("!")
						.toString();
				Game.addActivityLogItem(activity);
			}
			/**
			 * If neither of the above are true, send message to recipient.
			 */
			else {
				tradeoutgoingMessage = (TradeMessage) incomingMessage;
				tradeoutgoingMessage.setAccepted(false);
				socketMap.get(recipient.getName())
					.getBasicRemote()
					.sendObject(tradeoutgoingMessage);
			}
			GamestateMessage gamestateMessage = generateGamestateMessage();
			Iterator<Session> iterator = chatroomUsers.iterator();
			while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(gamestateMessage);
			
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
		System.out.println("Begin error log output:");
		System.out.println("Throwable message: " + throwable.getMessage());
		System.out.println(throwable.toString());
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