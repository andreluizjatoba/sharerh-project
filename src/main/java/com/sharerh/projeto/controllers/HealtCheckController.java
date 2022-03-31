package com.sharerh.projeto.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controle Rest para retornar uma mensagem e verifica se API está UP
 * 
 * @author andrejatoba
 *
 */

@RestController
@RequestMapping("v1/health")
public class HealtCheckController {
	
	@GetMapping	
	public String health() {
		return "It works Successfully";
	}
	
}
