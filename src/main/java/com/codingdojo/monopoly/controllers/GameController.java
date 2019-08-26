package com.codingdojo.monopoly.controllers;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;
import com.codingdojo.monopoly.scmodels.DiceMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
//@RequestMapping("/game")
public class GameController {
	// //TODO: Intended to be the routing controller for the Monopoly game.
	// Should send data to clients, and handle calling functions to
	// process game actions and the like.
	private static ArrayList<Player> playerArray = new ArrayList<>();

	@RequestMapping("/gameboardtest")
	public String gameBoardPage() {
		return "test.jsp";
	}
	
	@RequestMapping("/newplayer")
	public String index() {
		return "newplayer.jsp";
	}
	
	@RequestMapping(value="/createplayer", method=RequestMethod.POST)
	public String newplayer(@RequestParam("playername") String playername, Model model) {
		Player newplayer = new Player(playername);
		playerArray.add(newplayer);
		Game.addPlayer(newplayer);
		model.addAttribute("newplayer", newplayer);
		System.out.println("player size = "+playerArray.size());
		return "test.jsp";	
	}
	
	
	@RequestMapping("/roleDices")
	public String showdice() {
		System.out.println("hello from meeeeee");
		int dice1 = Game.rollDie();
		int dice2 = Game.rollDie();
		
		return null;
	}
	
	
	

	@RequestMapping("/placeholder")
	public String loginPage(HttpSession session, Model model) {
		return "placeholderForLoginPage.jsp";
	}
	
	@RequestMapping("/lobby")
	public String lobby(HttpSession session, Model model) {
		Player player1 = new Player("Steve");
		Player player2 = new Player("Paul");
		Player player3 = new Player("Susan");
		Player player4 = new Player("Charles");
		Game.init();
		Game.addPlayer(player1);
		Game.addPlayer(player2);
		Game.addPlayer(player3);
		Game.addPlayer(player4);
		model.addAttribute("players", Game.getPlayers());
		return "placeholderForLobbyPage.jsp";
	}
	
	@RequestMapping("/game")
	public String game(HttpSession session, Model model) {
		Gson gson = new GsonBuilder()
				.create();
		gson.toJson(Game.class);
		
		while(Game.isGameStarted() == true) {
			//For loop just to simulate turns, remove when we're done
			for(int i = 0; i < 6; i ++) {
				
				//Only get nextPlayer if double roll wasn't rolled
				if(Player.getDoubleRolls() == 0) {
					Game.nextPlayer();
				}
				Player currentPlayer = Game.getCurrentPlayer();
				
				//Moves current player to next spot
				currentPlayer.movePlayer();
				
				Game.doStuff(currentPlayer);
				
				//Check if player is now bankrupt after the turn, and add them to bankrupt list if they are
				if(currentPlayer.isBankrupt()) {
					Game.goBankrupt(currentPlayer);
				}
				
				//Check if game over when only 1 player left
				Game.setGameStarted(Game.isGameOver(Game.getPlayers()));
			}
		}
		model.addAttribute("players", Game.getPlayers());
		return "test.jsp";
	}
}
