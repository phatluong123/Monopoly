package com.codingdojo.monopoly.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

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
import com.codingdojo.monopoly.models.cards.GetOutOfJailFreeChance;
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
import com.google.gson.annotations.Expose;

@Component
public class Game {
	@Expose private static final HashMap<String, Integer> sets = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 522436266570487631L;

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
	@Expose private static final Space[] board = new Space[] {
			new OtherSpace("Go"),
			new Street("Mediterranean Avenue", 60, "brown", 2, 10, 30, 90, 160, 250, 50),
			new ActionSpace("Community Chest", "chest"),
			new Street("Baltic Avenue", 60, "brown", 4, 20, 60, 180, 320, 450, 50),
			new TaxSpace("Income Tax", 200),
			new Railroad("Reading Railroad"),
			new Street("Oriental Avenue", 100, "sky", 6, 30, 90, 270, 400, 550, 50),
			new ActionSpace("Chance", "chance"),
			new Street("Vermont Avenue", 100, "sky", 6, 30, 90, 270, 400, 550, 50),
			new Street("Connecticut Avenue", 120, "sky", 8, 40, 100, 300, 450, 600, 50),
			new OtherSpace("Jail"),
			new Street("St. Charles Place", 140, "purple", 10, 50, 150, 450, 625, 750, 100),
			new Utility("Electric Company"),
			new Street("States Avenue", 140, "purple", 10, 50, 150, 450, 625, 750, 100),
			new Street("Virginia Avenue", 160, "purple", 12, 60, 180, 500, 700, 900, 100),
			new Railroad("Pennsylvania Railroad"),
			new Street("St. James Place", 180, "orange", 14, 70, 200, 550, 750, 950, 100),
			new ActionSpace("Community Chest", "chest"),
			new Street("Tennessee Avenue", 180, "orange", 14, 70, 200, 550, 750, 950, 100),
			new Street("New York Avenue", 200, "orange", 16, 80, 220, 600, 800, 1000, 100),
			new OtherSpace("Free Parking"),
			new Street("Kentucky Avenue", 220, "red", 18, 90, 250, 700, 875, 1050, 150),
			new ActionSpace("Chance", "chance"),
			new Street("Indiana Avenue", 220, "red", 18, 90, 250, 700, 875, 1050, 150),
			new Street("Illinois Avenue", 240, "red", 20, 100, 300, 750, 925, 1050, 150),
			new Railroad("B. & O. Railroad"),
			new Street("Atlantic Avenue", 260, "yellow", 22, 110, 330, 800, 975, 1150, 150),
			new Street("Ventnor Avenue", 260, "yellow", 22, 110, 330, 800, 975, 1150, 150),
			new Utility("Water Works"),
			new Street("Marvin Gardens", 280, "yellow", 24, 120, 360, 850, 1025, 1200, 150),
			new OtherSpace("Go To Jail"),
			new Street("Pacific Avenue", 300, "green", 26, 130, 390, 900, 1100, 1275, 200),
			new Street("North Carolina Avenue", 300, "green", 26, 130, 390, 900, 1100, 1275, 200),
			new ActionSpace("Community Chest", "chest"),
			new Street("Pennsylvania Avenue", 320, "green", 28, 150, 450, 1000, 1200, 1400, 200),
			new Railroad("Short Line"),
			new ActionSpace("Chance", "chance"),
			new Street("Park Place", 350, "blue", 35, 175, 500, 1100, 1300, 1500, 200),
			new TaxSpace("Luxury Tax", 100),
			new Street("Boardwalk", 400, "blue", 50, 200, 600, 1400, 1700, 2000, 200)
	};
	@Expose private static ArrayList<Player> players = new ArrayList<>();
	@Expose private static ArrayList<Player> bankruptPlayers = new ArrayList<>();
	@Expose private static Integer turn = 0;
	@Expose private static Integer currentPlayerIndex = -1;
	@Expose private static boolean gameStarted = false;
	@Expose private static int[] lastDiceRoll = { 1, 1 };

	// Create Community Deck Card
	private static ArrayList<CommunityChestCard> communityDeck = new ArrayList<>();
	// Create Chance Deck Card
	private static ArrayList<ChanceCard> chanceDeck = new ArrayList<>();

	
	public Game() {}
	
	/**
	 * Force all variables to reset to default
	 */
	public static void init() {
		initDecks();
		shuffleDecks();
		players = new ArrayList<>();
		bankruptPlayers = new ArrayList<>();
		turn = 0;
		currentPlayerIndex = -1;
		gameStarted = false;
		lastDiceRoll[0] = 1;
		lastDiceRoll[1] = 1;
	}
	
	/**
	 * Clear and re-initialize all cards.
	 */
	private static void initDecks() {
		communityDeck.clear();
		chanceDeck.clear();
		communityDeck.add(new HospitalFee());
		communityDeck.add(new DoctorFee());
		communityDeck.add(new HolidayFundMatures());
		communityDeck.add(new BankError());
		communityDeck.add(new ConsultancyFee());
		communityDeck.add(new SchoolFee());
		communityDeck.add(new GoToJail());
		communityDeck.add(new GetOutJailFreeCommunity());
		communityDeck.add(new AssessedForStreetRepair());
		communityDeck.add(new ItIsYourBirthday());
		communityDeck.add(new InsuranceMatures());
		communityDeck.add(new WonBeautyContest());
		communityDeck.add(new SaleStock());
		communityDeck.add(new AdvanceToGo());
		communityDeck.add(new TaxRefund());
		communityDeck.add(new Inherit());
		chanceDeck.add(new GoBack3Spaces());
		chanceDeck.add(new AdvanceToReadingRailroad());
		chanceDeck.add(new AdvanceToBoardwalk());
		chanceDeck.add(new AdvanceToGoChance());
		chanceDeck.add(new AdvanceToIllinoisAve());
		chanceDeck.add(new BuildingLoanMatures());
		chanceDeck.add(new AdvanceToRailroad());
		chanceDeck.add(new AdvanceToRailroad());
		chanceDeck.add(new BankPaysYou());
		chanceDeck.add(new AdvanceToUtilityCard());
		chanceDeck.add(new GetOutOfJailFreeChance());
	}
	
	//Incomplete method for the general action of the game
	public static void doStuff(Player p) {
		Space[] board = Game.getBoard();
		Space currentSpace = board[p.getCurrentLocation()];
		String spaceName = currentSpace.getName();
		if(currentSpace instanceof Property) {
			Property currentProperty = (Property) currentSpace;
			if(currentProperty.getOwnedBy() != null && currentProperty.getOwnedBy() != p) {
				//Call function to deduct from current player and give money to owner player, send in current player
				p.payRent();
			}
			else {
				//Holder method for whatever we decide to implement to buy property if no owner
			}
		}
		else if(currentSpace instanceof OtherSpace) {
			//Do nothing if go, free parking or jail
			if(spaceName == "Go" || spaceName == "Free Parking" || spaceName == "Jail") {
				return;
			}
			else {
				//Set in jail to true, set currentLocation to jail
				p.goToJail();
			}
		}
		else if(currentSpace instanceof ActionSpace) {
			if(spaceName =="Community Chest") {
				ActionCard card = getCard("chest");
				//Add method to do action of the card
			}
			else {
				ActionCard card = getCard("chance");
				//Add method to do action of the card
			}
		}
		else if(currentSpace instanceof TaxSpace) {
			if(spaceName == "Income Tax") {
				p.pay(200);
			}
			else {
				p.pay(100);
			}
		}
		
	}
	
	//Method to check if game is over
	public static boolean isGameOver(ArrayList<Player> players) {
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
	
	public static void putCard(ActionCard card, String deck) {
		switch(deck) {
		case "chance":
			Game.putChanceCard((ChanceCard)card);
			return;
		case "chest":
			Game.putChestCard((CommunityChestCard)card);
			return;
		default:
			return;
		}
	}
	
	public static void putChanceCard(ChanceCard card) {
		chanceDeck.add(0, card);
	}

	public static void putChestCard(CommunityChestCard card) {
		communityDeck.add(0, card);
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
