package com.practice.springmongo.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoException;
import com.practice.springmongo.model.UserDetails;
import com.practice.springmongo.repository.UserRepository;
import com.practice.springmongo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/addUser")
	public UserDetails saveUser(@RequestBody UserDetails user) {
		return service.save(user);
	}
	
	@GetMapping("/findAllUsers")
	public List<UserDetails> getUsers() {
		return service.fetchAllUsers();
	}

	@GetMapping("/search/{input}")
	public List<UserDetails> search(@PathVariable String input){
		return service.searchDetails(input);
	}

}
