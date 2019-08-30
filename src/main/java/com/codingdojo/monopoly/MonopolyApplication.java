package com.codingdojo.monopoly;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codingdojo.monopoly.models.Game;

@SpringBootApplication
public class MonopolyApplication {
	public static final Set<Session> gameUsers = Collections.synchronizedSet(new HashSet<Session>());
	public static Integer userCount = 0;
	
	public static void main(String[] args) {
		Game.init();
		SpringApplication.run(MonopolyApplication.class, args);
		
	}
	

}
