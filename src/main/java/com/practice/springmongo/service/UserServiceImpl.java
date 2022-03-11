package com.practice.springmongo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.practice.springmongo.model.UserDetails;
import com.practice.springmongo.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
public List<UserDetails> searchDetails(String input) {
	return repository.fullTextSearch(input);
}
@Override
public UserDetails save(UserDetails user) {
	return repository.save(user);
}
@Override
public List<UserDetails>fetchAllUsers() {
	return repository.find();
}




}
