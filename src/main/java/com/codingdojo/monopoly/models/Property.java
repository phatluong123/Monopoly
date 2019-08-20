package com.codingdojo.monopoly.models;

import org.springframework.stereotype.Component;

@Component
public abstract class Property extends Space {
	private int purchaseValue;
}
