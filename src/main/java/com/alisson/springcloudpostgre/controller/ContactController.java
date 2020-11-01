package com.alisson.springcloudpostgre.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alisson.springcloudpostgre.repository.*;
import com.alisson.springcloudpostgre.model.*;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {
	
	private ContactRepository repository;
	
	ContactController(ContactRepository repository) {
		this.repository = repository;
	}
	
	// @RequestMapping(value="/contacts", method=RequestMethod.GET)
	@GetMapping
	public List<Contact> getAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Contact> findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

}
