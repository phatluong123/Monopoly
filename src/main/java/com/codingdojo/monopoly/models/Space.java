package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Space {
	private String name;
	public Space() {}
	public Space(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
