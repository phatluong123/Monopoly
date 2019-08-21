package com.codingdojo.monopoly.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.monopoly.models.Game;

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
		// In here will be the logic for connecting, instantiating, and adding
		// players to the game.
		model.addAttribute("players", Game.getPlayers());
		return "placeholderForLobbyPage.jsp";
	}
	
	@RequestMapping("/game")
	public String game(HttpSession session, Model model) {
		model.addAttribute("players", Game.getPlayers());
		return "test.jsp";
	}
}
