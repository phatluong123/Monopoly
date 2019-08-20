package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Game {
	Space[] board = new Space[] {
			new OtherSpace("Go"),
			new Street("Mediterranean Avenue", 60, "brown", 2, 4, 10, 30, 90, 160, 250, 50),
			new ActionSpace("Community Chest", "chest"),
			new Street("Baltic Avenue", 60, "brown", 4, 8, 20, 60, 180, 320, 450, 50),
			new TaxSpace("Income Tax", 200),
			new Railroad("Reading Railroad"),
			new Street("Oriental Avenue"),
			new ActionSpace("Chance", "chance"),
			new Street("Vermont Avenue"),
			new Street("Connecticut Avenue"),
			new OtherSpace("Jail"),
			new Street("St. Charles Place"),
			new Utility("Electric Company"),
			new Street("States Avenue"),
			new Street("Virginia Avenue"),
			new Railroad("Pennsylvania Railroad"),
			new Street("St. James Place"),
			new ActionSpace("Community Chest", "chest"),
			new Street("Tennessee Avenue"),
			new Street("New York Avenue"),
			new OtherSpace("Free Parking"),
			new Street("Kentucky Avenue"),
			new ActionSpace("Chance", "chance"),
			new Street("Indiana Avenue")
	};
}
