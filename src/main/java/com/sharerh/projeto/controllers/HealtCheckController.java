package com.sharerh.projeto.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/health")
public class HealtCheckController {
	
	@GetMapping	
	public String health() {
		return "It works Successfully";
	}
	
}
