package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.codingdojo.monopoly.models.cards.ActionCard;

@Component
public class Game {
	private static final HashMap<String, Integer> sets = new HashMap<String, Integer>() {
		{
			put("railroad", 4);
			put("utility", 2);
			put("brown", 2);
			put("sky", 3);
			put("purple", 3);
			put("orange", 3);
			put("red", 3);
			put("yellow", 3);
			put("green", 3);
			put("blue", 2);
		}
	};
	private static final Space[] board = new Space[] {
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
	private static ArrayList<Player> players = new ArrayList<>();
	private static ArrayList<Player> bankruptPlayers = new ArrayList<>();
	private static Integer turn = 0;
	private static Integer currentPlayerIndex = 0;
	private static boolean gameStarted = false;
	private static int[] lastDiceRoll = { 1, 1 };

	// Create Community Deck Card
	private ArrayList<ActionCard> CommunityCards = new ArrayList<>();

	private final ActionCard HospitalFee;
	private final ActionCard HodidayFundMatures;
	private final ActionCard DoctorFee;
	private final ActionCard BankError;
	private final ActionCard ConsultancyFee;
	private final ActionCard SchoolFee;
	private final ActionCard GoToJail;
	private final ActionCard GetOutJailFreeCommunity;
	private final ActionCard AssessedForStreetRepair;
	private final ActionCard IsYourBirthday;
	private final ActionCard InsuranceMatures;
	private final ActionCard WonBeautyContest;
	private final ActionCard SaleStock;
	private final ActionCard AdvanceToGo;
	private final ActionCard TaxRefund;
	private final ActionCard Inherit;

	public void addCommunityCards() {
		CommunityCards.add(HospitalFee);
		CommunityCards.add(DoctorFee);
		CommunityCards.add(HodidayFundMatures);
		CommunityCards.add(BankError);
		CommunityCards.add(ConsultancyFee);
		CommunityCards.add(SchoolFee);
		CommunityCards.add(GoToJail);
		CommunityCards.add(GetOutJailFreeCommunity);
		CommunityCards.add(AssessedForStreetRepair);
		CommunityCards.add(IsYourBirthday);
		CommunityCards.add(InsuranceMatures);
		CommunityCards.add(WonBeautyContest);
		CommunityCards.add(SaleStock);
		CommunityCards.add(AdvanceToGo);
		CommunityCards.add(TaxRefund);
		CommunityCards.add(Inherit);
	}

	// Create Chance Deck Card
	private ArrayList<ActionCard> ChanceCards = new ArrayList<>();

	private final ActionCard GoBack3Spaces;
	private final ActionCard AdvanceToReadingRailroad;
	private final ActionCard AdvanceToBoardwalk;
	private final ActionCard AdvanceToGoChance;
	private final ActionCard AdvanceToIllinoisAve;
	private final ActionCard BuildingLoanMatures;
	private final ActionCard AdvanceToRailroad;
	private final ActionCard AdvanceToRailroad2;
	private final ActionCard BankPaysYou;
	private final ActionCard AdvanceToUtilityCard;

	public void addChanceCards() {
		ChanceCards.add(GoBack3Spaces);
		ChanceCards.add(AdvanceToReadingRailroad);
		ChanceCards.add(AdvanceToBoardwalk);
		ChanceCards.add(AdvanceToGoChance);
		ChanceCards.add(AdvanceToIllinoisAve);
		ChanceCards.add(BuildingLoanMatures);
		ChanceCards.add(AdvanceToRailroad);
		ChanceCards.add(AdvanceToRailroad2);
		ChanceCards.add(BankPaysYou);
		ChanceCards.add(AdvanceToUtilityCard);

	}

	
	
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
		
		userInput.close();
	}

	public static HashMap<String, Integer> getSets() {
		return sets;
	}

	public static Space[] getBoard() {
		return board;
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static Integer getTurn() {
		return turn;
	}

	public static Integer getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public static ArrayList<Player> getBankruptPlayers() {
		return bankruptPlayers;
	}

	public static boolean isGameStarted() {
		return gameStarted;
	}

	public static int[] getLastDiceRoll() {
		return lastDiceRoll;
	}

	public static void setCurrentPlayerIndex(Integer currentPlayerIndex) {
		Game.currentPlayerIndex = currentPlayerIndex;
	}

	public static void setPlayers(ArrayList<Player> players) {
		Game.players = players;
	}

	public static void setTurn(Integer turn) {
		Game.turn = turn;
	}
	
	public static void setBankruptPlayers(ArrayList<Player> bankruptPlayers) {
		Game.bankruptPlayers = bankruptPlayers;
	}

	public static void setGameStarted(boolean gameStarted) {
		Game.gameStarted = gameStarted;
	}

	public static void setLastDiceRoll(int[] lastDiceRoll) {
		Game.lastDiceRoll = lastDiceRoll;
	}

	public static boolean ownsFullSet(Player player, String set) {
		return player.getSetsOwned().get(set) == sets.get(set);
	}
	
	public static void addPlayer(Player player) {
		Game.players.add(player);
	}
	
	public static void addPlayer(String name) {
		Game.players.add(new Player(name));
	}
	
	public static void addPlayer() {
		Game.players.add(new Player("Player ".concat(Integer.toString(Game.players.size() + 1))));
	}
	
	public static Player getCurrentPlayer() {
		return Game.players.get(Game.currentPlayerIndex);
	}
	
	public static void nextPlayer() {
		if(Game.currentPlayerIndex == Game.players.size() - 1) {
			Game.nextTurn();
			Game.currentPlayerIndex = 0;
		} else {
			Game.currentPlayerIndex++;
		}
	}
	
	public static int rollDie() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
	public static int[] rollDice() {
		Random r = new Random();
		int dice1 = r.nextInt(6) + 1;
		int dice2 = r.nextInt(6) + 1;
		return new int[] {dice1, dice2};
	}
	
	public static void nextTurn() {
		Game.turn++;
	}
	
	public static int getTotalMoneyInPlay() {
		int sum = 0;
		for(Player p: Game.players) {
			sum += p.getMoney();
		}
		return sum;
	}
	
	public static void goBankrupt(Player player) {
		Game.players.remove(player);
		Game.bankruptPlayers.add(player);
	}
}
