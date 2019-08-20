package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class Utility extends Property {
	
	public Utility(String name) {
		super(name, 150);
	}
}