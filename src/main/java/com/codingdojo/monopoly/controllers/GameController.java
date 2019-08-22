package com.codingdojo.monopoly.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

@Controller
@RequestMapping("/game")
public class GameController {
	// //TODO: Intended to be the routing controller for the Monopoly game.
	// Should send data to clients, and handle calling functions to
	// process game actions and the like.
	
	@RequestMapping("")
	public String loginPage(HttpSession session, Model model) {
		return "placeholderForLoginPage.jsp";
	}
	
	@RequestMapping("/lobby")
	public String lobby(HttpSession session, Model model) {
		Player player1 = new Player("Steve");
		Player player2 = new Player("Paul");
		Player player3 = new Player("Susan");
		Game.addPlayer(player1);
		Game.addPlayer(player2);
		Game.addPlayer(player3);
		model.addAttribute("players", Game.getPlayers());
		return "placeholderForLobbyPage.jsp";
	}
	
	@RequestMapping("/game")
	public String game(HttpSession session, Model model) {
		while(Game.isGameStarted() == true) {
			//For loop just to simulate turns, remove when we're don
			for(int i = 0; i < 6; i ++) {
				
				//Only get nextPlayer if double roll wasn't rolled
				if(Player.getDoubleRolls() == 0) {
					Game.nextPlayer();
				}
				Player currentPlayer = Game.getPlayers().get(Game.getCurrentPlayerIndex());
				
				//Moves current player to next spot
				currentPlayer.movePlayer(currentPlayer);
				
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
