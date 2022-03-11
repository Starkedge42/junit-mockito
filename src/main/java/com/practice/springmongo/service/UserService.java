package com.practice.springmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.practice.springmongo.model.UserDetails;

@Service
public interface UserService {

	public UserDetails save(UserDetails user);
	
	public List<UserDetails> searchDetails(String input);

	public List<UserDetails> fetchAllUsers();
}
