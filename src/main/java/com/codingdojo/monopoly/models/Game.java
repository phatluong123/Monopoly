package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.codingdojo.monopoly.models.cards.ActionCard;
import com.codingdojo.monopoly.models.cards.AdvanceToBoardwalk;
import com.codingdojo.monopoly.models.cards.AdvanceToGo;
import com.codingdojo.monopoly.models.cards.AdvanceToGoChance;
import com.codingdojo.monopoly.models.cards.AdvanceToIllinoisAve;
import com.codingdojo.monopoly.models.cards.AdvanceToRailroad;
import com.codingdojo.monopoly.models.cards.AdvanceToReadingRailroad;
import com.codingdojo.monopoly.models.cards.AdvanceToUtilityCard;
import com.codingdojo.monopoly.models.cards.AssessedForStreetRepair;
import com.codingdojo.monopoly.models.cards.BankError;
import com.codingdojo.monopoly.models.cards.BankPaysYou;
import com.codingdojo.monopoly.models.cards.BuildingLoanMatures;
import com.codingdojo.monopoly.models.cards.ChanceCard;
import com.codingdojo.monopoly.models.cards.CommunityChestCard;
import com.codingdojo.monopoly.models.cards.ConsultancyFee;
import com.codingdojo.monopoly.models.cards.DoctorFee;
import com.codingdojo.monopoly.models.cards.GetOutJailFreeCommunity;
import com.codingdojo.monopoly.models.cards.GoBack3Spaces;
import com.codingdojo.monopoly.models.cards.GoToJail;
import com.codingdojo.monopoly.models.cards.HolidayFundMatures;
import com.codingdojo.monopoly.models.cards.HospitalFee;
import com.codingdojo.monopoly.models.cards.Inherit;
import com.codingdojo.monopoly.models.cards.InsuranceMatures;
import com.codingdojo.monopoly.models.cards.ItIsYourBirthday;
import com.codingdojo.monopoly.models.cards.SaleStock;
import com.codingdojo.monopoly.models.cards.SchoolFee;
import com.codingdojo.monopoly.models.cards.TaxRefund;
import com.codingdojo.monopoly.models.cards.WonBeautyContest;
import com.example.gameTester.ActionSpace;
import com.example.gameTester.Game;
import com.example.gameTester.OtherSpace;
import com.example.gameTester.Player;
import com.example.gameTester.Property;
import com.example.gameTester.Space;
import com.example.gameTester.TaxSpace;

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
	
	private static ArrayList<CommunityChestCard> communityDeck = new ArrayList<CommunityChestCard>() {{
		add(new HospitalFee("Paying Hospital Fees Pay $100"));
		add(new DoctorFee("Doctor's Fees Pay $50"));
		add(new HolidayFundMatures("Holiday Fund Matures Receive $100"));
		add(new BankError("Bank error in your favor. Collect $200."));
		add(new ConsultancyFee("Receive $25 consultancy fee"));
		add(new SchoolFee("School fees. Pay $50"));
		add(new GoToJail("Go To Jail"));
		add(new GetOutJailFreeCommunity("Get Out of Jail Free"));
		add(new AssessedForStreetRepair("You are assessed for street repairs: Pay $40 per house and $115 per hotel you own."));
		add(new ItIsYourBirthday("It is your birthday. Collect $10 from every players"));
		add(new InsuranceMatures("Life insurance matures – Collect $100"));
		add(new WonBeautyContest("You have won second prize in a beauty contest. Collect $10."));
		add(new SaleStock("From sale of stock you get $50."));
		add(new AdvanceToGo("Advance to \"Go\". (Collect $200) "));
		add(new TaxRefund("Income tax refund. Collect $20. "));
		add(new Inherit("You inherit $100."));
	}};


	// Create Chance Deck Card
	private static ArrayList<ChanceCard> chanceDeck = new ArrayList<ChanceCard>() {{
		add(new GoBack3Spaces("Go Back Three Spaces."));
		add(new AdvanceToReadingRailroad("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));
		add(new AdvanceToBoardwalk("Advance To BoardWalk"));
		add(new AdvanceToGoChance("Advance to go Collect $200"));
		add(new AdvanceToIllinoisAve("Advance to Illinois Ave. {Avenue}. If you pass Go, collect $200. "));
		add(new BuildingLoanMatures("Your Building Loan Matures Collect $150"));
		add(new AdvanceToRailroad("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. "));
		add(new AdvanceToRailroad("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. "));
		add(new BankPaysYou("Bank pays you dividend of $50."));
		add(new AdvanceToUtilityCard("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total 10 times the amount thrown. "));
		
	}};

	
	public Game() {
//		Scanner userInput = new Scanner(System.in);
//		System.out.println("Enter how many players:");
//		int totalPlayers = Integer.parseInt(userInput.nextLine());
//		boolean moveOn = false;
//		while(moveOn == false) {
//			if(totalPlayers < 2 || totalPlayers > 4) {
//				System.out.println("You must enter a number between 2-4");
//				totalPlayers = Integer.parseInt(userInput.nextLine());
//			}
//			else {
//				moveOn = true;
//			}
//		}
//		
//		ArrayList<Player> playerList = new ArrayList<Player>();
//		
//		for(int i = 0; i < totalPlayers; i++) {
//			System.out.println("Enter name for player " + i + ":");
//			String newName = userInput.nextLine();
//			Player player = new Player(newName);
//			playerList.add(player);
//		}
//		
//		userInput.close();
		Game game = new Game();
		int currentPlayer = 0;
		boolean gameStatus = true;
		
		//Added from my test class, some method names will need to be renamed
		while(gameStatus == true) {
			for(int i = 0; i < 27; i++) {
				
				//Check if game boolean is false
				gameStatus = game.isGameOver(players);
				
				//If player rolled a double, don't increment to next player yet
				if(Game.getDoubleRolls() != 0) {
					currentPlayer = Game.getWhoNext();
				}
				else {
					currentPlayer = game.getNextPlayer(players);
				}
				game.movePlayer(players.get(currentPlayer), board);
				//Purchase property etc...
				game.doStuff(players.get(currentPlayer));
				
				//Printing array to show player locations
				for(int j = 0; j < board.length; j++) {
					if(j == 39) {
						System.out.print(board[j]);
					}
					else {
						System.out.print(board[j]+", ");
					}
				}
				System.out.println();
				
				//Check if bankrupt
				game.isBankrupt(playerList, playerList.get(currentPlayer), i, board);
			}
		}
	}
	//Incomplete method for the general action of the game
	public void doStuff(Player p) {
		Space[] board = Game.getBoard();
		Space currentSpace = board[p.getCurrentLocation()];
		String spaceName = board[currentSpace].getName();
		if(board[currentSpace] instanceof Property) {
			if(currentSpace.getOwnedBy() != null) {
				//Get rent cost if property is already owned
				int rent = currentSpace.getRentCost();
				//Call function to deduct from current player and give money to owner player, send in current player
				p.payRent(p);
			}
			else {
				//Holder method for whatever we decide to implement to buy property if no owner
				purchaseProperty(currentSpace, p);
			}
		}
		else if(board[currentSpace] instanceof OtherSpace) {
			//Do nothing if go, free parking or jail
			if(spaceName == "Go" || spaceName == "Free Parking" || spaceName == "Jail") {
				return;
			}
			else {
				//Set in jail to true, set currentLocation to jail
				p.setInJail = true;
				p.setCurrentLocation(10);
				return;
			}
		}
		else if(board[currentSpace] instanceof ActionSpace) {
			if(spaceName =="Community Chest") {
				ActionCard = getCard("chest");
				//Add method to do action of the card
			}
			else {
				ActionCard card = getCard("chance");
				//Add method to do action of the card
			}
		}
		else if(board[currentSpace] instanceof TaxSpace) {
			if(spaceName == "Income Tax") {
				//insert method to have player pay 200
			}
			else {
				//Insert method to have player pay 100
			}
		}
		
	}
	
	//Method to check if game is overco
	public boolean isGameOver(ArrayList<Player> players) {
		if(players.size() == 1) {
			return false;
		}
		else {
			return true;
		}
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
	
	public static ArrayList<CommunityChestCard> getCommunityDeck() {
		return communityDeck;
	}
	
	public static ArrayList<ChanceCard> getChanceDeck() {
		return chanceDeck;
	}
	
	public static ActionCard getCard(String deck) {
		if(deck == "chance") {
			return chanceDeck.remove(chanceDeck.size()-1);
		} else if (deck == "chest") {
			return communityDeck.remove(communityDeck.size()-1);
		} else {
			return null;
		}
	}
	
	public static ChanceCard getChanceCard() {
		return chanceDeck.remove(chanceDeck.size()-1);
	}
	
	public static CommunityChestCard getCommunityChestCard () {
		return communityDeck.remove(communityDeck.size()-1);
	}
	
	public static void nextPlayer() {
		if(Game.currentPlayerIndex >= Game.players.size() - 1) {
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
	
	public static void shuffleDecks() {
		Collections.shuffle(chanceDeck);
		Collections.shuffle(communityDeck);
	}
}
