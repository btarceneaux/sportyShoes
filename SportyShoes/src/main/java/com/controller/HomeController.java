package com.controller;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EntityScan("com.bean")
public class HomeController 
{
	@GetMapping("/")
	public String displayHome()
	{	
		return "index";
	}

}
