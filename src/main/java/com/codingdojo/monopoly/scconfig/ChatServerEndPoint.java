package com.codingdojo.monopoly.scconfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.DiceMessage;
import com.codingdojo.monopoly.scmodels.Message;
import com.codingdojo.monopoly.scmodels.UserMessage;



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
		if (incomingMessage instanceof ChatMessage) {

			ChatMessage incomingChatMessage = (ChatMessage) incomingMessage;
			
		
			String username = (String) userSession.getUserProperties().get("username");
			System.out.println(incomingChatMessage.getMessage());
			if (incomingChatMessage.getMessage().equals("roll")) {
				DiceMessage diceoutgoingMessage = new DiceMessage();
				Integer dice1 = Game.rollDie();
				Integer dice2 = Game.rollDie();
				System.out.println("dice1= " +dice1 + "dice2 = " + dice2);
				
				diceoutgoingMessage.setName(username);
				diceoutgoingMessage.setDice1(dice1);
				diceoutgoingMessage.setDice2(dice2); 
				userSession.getBasicRemote().sendObject(diceoutgoingMessage);
				Iterator<Session> iterator = chatroomUsers.iterator();
				
				while (iterator.hasNext()) iterator.next().getBasicRemote().sendObject(diceoutgoingMessage);
			}
			else if (username == null) {
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
		System.out.println(throwable.getStackTrace().toString());
		System.out.println(throwable.getMessage());
	}
	
	
}