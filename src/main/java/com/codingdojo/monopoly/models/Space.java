package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
public abstract class Space {
	@Expose private String name;
	
	public Space() {
		
	}
	
	public Space(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static Space findSpaceByName(String name) {
		for(int i = 0; i < Game.getBoard().length; i++) {
			if(Game.getBoard()[i].getName().equals(name)) {
				return Game.getBoard()[i];
			}
		}
		return null;
	}
}
