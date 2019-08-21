package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class Game {
	// Instantiates a new board (an array of 40 spaces)
	// Each space is defined, as per a standard Monopoly game.
	// Index 0 = Go, index 39 = Boardwalk.
	private static Space[] board = new Space[] {
			new OtherSpace("Go"),
			new Street("Mediterranean Avenue", 60, "brown", 2, 4, 10, 30, 90, 160, 250, 50),
			new ActionSpace("Community Chest", "chest"),
			new Street("Baltic Avenue", 60, "brown", 4, 8, 20, 60, 180, 320, 450, 50),
			new TaxSpace("Income Tax", 200),
			new Railroad("Reading Railroad"),
			new Street("Oriental Avenue", 100, "sky", 6, 12, 30, 90, 270, 400, 550, 50),
			new ActionSpace("Chance", "chance"),
			new Street("Vermont Avenue", 100, "sky", 6, 12, 30, 90, 270, 400, 550, 50),
			new Street("Connecticut Avenue", 120, "sky", 8, 16, 40, 100, 300, 450, 600, 50),
			new OtherSpace("Jail"),
			new Street("St. Charles Place", 140, "purple", 10, 20, 50, 150, 450, 625, 750, 100),
			new Utility("Electric Company"),
			new Street("States Avenue", 140, "purple", 10, 20, 50, 150, 450, 625, 750, 100),
			new Street("Virginia Avenue", 160, "purple", 12, 24, 60, 180, 500, 700, 900, 100),
			new Railroad("Pennsylvania Railroad"),
			new Street("St. James Place", 180, "orange", 14, 28, 70, 200, 550, 750, 950, 100),
			new ActionSpace("Community Chest", "chest"),
			new Street("Tennessee Avenue", 180, "orange", 14, 28, 70, 200, 550, 750, 950, 100),
			new Street("New York Avenue", 200, "orange", 16, 32, 80, 220, 600, 800, 1000, 100),
			new OtherSpace("Free Parking"),
			new Street("Kentucky Avenue", 220, "red", 18, 36, 90, 250, 700, 875, 1050, 150),
			new ActionSpace("Chance", "chance"),
			new Street("Indiana Avenue", 220, "red", 18, 36, 90, 250, 700, 875, 1050, 150),
			new Street("Illinois Avenue", 240, "red", 20, 40, 100, 300, 750, 925, 1050, 150),
			new Railroad("B. & O. Railroad"),
			new Street("Atlantic Avenue", 260, "yellow", 22, 44, 110, 330, 800, 975, 1150, 150),
			new Street("Ventnor Avenue", 260, "yellow", 22, 44, 110, 330, 800, 975, 1150, 150),
			new Utility("Water Works"),
			new Street("Marvin Gardens", 280, "yellow", 24, 48, 120, 360, 850, 1025, 1200, 150),
			new OtherSpace("Go To Jail"),
			new Street("Pacific Avenue", 300, "green", 26, 52, 130, 390, 900, 1100, 1275, 200),
			new Street("North Carolina Avenue", 300, "green", 26, 52, 130, 390, 900, 1100, 1275, 200),
			new ActionSpace("Community Chest", "chest"),
			new Street("Pennsylvania Avenue", 320, "green", 28, 56, 150, 450, 1000, 1200, 1400, 200),
			new Railroad("Short Line"),
			new ActionSpace("Chance", "chance"),
			new Street("Park Place", 350, "blue", 35, 70, 175, 500, 1100, 1300, 1500, 200),
			new TaxSpace("Luxury Tax", 100),
			new Street("Boardwalk", 400, "blue", 50, 100, 200, 600, 1400, 1700, 2000, 200)
	};
	private static ArrayList<Player> players;

	public Game() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter how many players:");
		int totalPlayers = Integer.parseInt(userInput.nextLine());
		boolean moveOn = false;
		while(moveOn == false) {
			if(totalPlayers < 2 || totalPlayers > 4) {
				System.out.println("You must enter a number between 2-4");
				totalPlayers = Integer.parseInt(userInput.nextLine());
			}
			else {
				moveOn = true;
			}
		}
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		for(int i = 0; i < totalPlayers; i++) {
			System.out.println("Enter name for player " + i + ":");
			String newName = userInput.nextLine();
			Player player = new Player(newName);
			playerList.add(player);
		}
	}
	public Game(List<Player> players) {
		Game.players = (ArrayList<Player>)players;
	}
	
	public static void addPlayer(Player player) {
		Game.players.add(player);
	}
	
	public static void setPlayers(List<Player> players) {
		Game.players = (ArrayList<Player>) players;
	}
	
	public static Space[] getBoard() {
		return Game.board;
	}
	
	public static ArrayList<Player> getPlayers() {
		return players;
	}
}
