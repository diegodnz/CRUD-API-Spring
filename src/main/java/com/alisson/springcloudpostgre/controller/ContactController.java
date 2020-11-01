package com.alisson.springcloudpostgre.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisson.springcloudpostgre.repository.*;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {
	
	private ContactRepository repository;
	
	ContactController(ContactRepository repository) {
		this.repository = repository;
	}

}
