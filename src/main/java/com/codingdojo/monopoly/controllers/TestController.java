package com.codingdojo.monopoly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/")
	public String showpage() {
		return "test.jsp";
	}
	
}
