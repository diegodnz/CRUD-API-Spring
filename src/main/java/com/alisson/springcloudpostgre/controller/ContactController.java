package com.alisson.springcloudpostgre.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alisson.springcloudpostgre.repository.*;
import com.alisson.springcloudpostgre.model.Contact;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {
	
	private ContactRepository repository;
	
	ContactController(ContactRepository repository) {
		this.repository = repository;
	}
	
	// Retrieve All Contacts
	// @RequestMapping(value="/contacts", method=RequestMethod.GET)
	@GetMapping
	public List<Contact> getAll() {
		return repository.findAll();
	}
	
	// Retrieve a Contact by Id
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Contact> findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	// Create a new Contact
	@PostMapping
	public Contact create(@RequestBody Contact contact) {
		return repository.save(contact);
	}
	/* 	 
    "name": "Java",
    "email": "java@gmail.com",
    "phone": "(111) 111-1111"    
	 */
	
	
	// Update a Contact
	@PutMapping(value="/{id}")
	public ResponseEntity<Contact> update(@PathVariable("id") long id,
			                              @RequestBody Contact contact) {
		return repository.findById(id)
				.map(record -> {
					record.setName(contact.getName());
					record.setEmail(contact.getEmail());
					record.setPhone(contact.getPhone());
					Contact updated = repository.save(record);
					return ResponseEntity.ok().body(updated);					
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	// Remove a Contact
	@DeleteMapping(path= {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
