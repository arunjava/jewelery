package com.nura.jewelery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomePageController {

	@GetMapping(value = "/login")
	public String index() {
		return "forward:/index.html";
	}
	
//	@GetMapping(value = "/**/{path:[^.]*}")
//	public String default() {
//		return "forward:/index.html";
//	}
	
}
