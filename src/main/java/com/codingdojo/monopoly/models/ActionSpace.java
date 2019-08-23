package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public class ActionSpace extends Space {
	public ActionSpace() {
		super();
	}

	public ActionSpace(String name, String type) {
		super(name);
	}
}
