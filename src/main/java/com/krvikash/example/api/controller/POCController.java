package com.krvikash.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc")
public class POCController {

	@GetMapping("/{name}")
	public String getInfo(@PathVariable(name="name") String name) {
		return "Welcome: "+name;
	}	
}
