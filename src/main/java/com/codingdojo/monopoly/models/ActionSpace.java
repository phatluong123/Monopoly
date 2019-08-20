package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class ActionSpace extends Space {
	private String type;

	public ActionSpace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionSpace(String name, String type) {
		super(name);
		this.type = type;
		// TODO Auto-generated constructor stub
	}
}
