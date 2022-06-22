package com.guairaca.tec.soccerteams;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path="/teste")
public class TestController {

	@GetMapping
	public String teste() {
		return "sucesso";
	}

}
