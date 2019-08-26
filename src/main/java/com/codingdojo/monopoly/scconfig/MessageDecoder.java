package com.codingdojo.monopoly.scconfig;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.codingdojo.monopoly.models.Player;
import com.codingdojo.monopoly.scmodels.ChatMessage;
import com.codingdojo.monopoly.scmodels.Message;

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

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setMessage(jsonObject.getString("message"));
		System.out.println(chatMessage);
		return chatMessage;
	}
	
	

	@Override
	public boolean willDecode(String jsonMessage) {
		boolean flag = true;
		
		try { Json.createReader(new StringReader(jsonMessage)).readObject() ;}
		catch (Exception e) {flag = false;}
		return flag;
	}
	
}
