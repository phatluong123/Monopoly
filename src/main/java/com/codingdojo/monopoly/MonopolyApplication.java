package com.codingdojo.monopoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codingdojo.monopoly.models.Game;

@SpringBootApplication
public class MonopolyApplication {

	public static void main(String[] args) {
		Game.init();
		SpringApplication.run(MonopolyApplication.class, args);
	}
	

}
