package com.practice.springmongo.repository;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.practice.springmongo.model.UserDetails;
@Repository
public class UserRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public UserDetails save(UserDetails user) {
		return mongoTemplate.save(user);
	}
	
	public List<UserDetails> find() {
		return mongoTemplate.findAll(UserDetails.class);
	}
	public List<UserDetails> fullTextSearch(String searchPhrase) {
	    TextCriteria criteria = TextCriteria
	                                .forDefaultLanguage().matching(searchPhrase);
	    
	    Query query = TextQuery.queryText(criteria);
	    
	    List<UserDetails> userDetails = mongoTemplate.find(query, UserDetails.class);

	    return userDetails;
	}
	//partial matching using OR 
	public List<UserDetails> patternSearch(String term){
		Query query=new Query();
		final List<Criteria> criteria = new ArrayList<>();
		if(term.length()>0) {
			criteria.add(Criteria.where("firstName").regex(term,"i"));
			criteria.add(Criteria.where("lastName").regex(term,"i"));
			criteria.add(Criteria.where("profession").regex(term,"i"));
		}
		if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[0])));
        }
		return mongoTemplate.find(query, UserDetails.class);
	}
	//Partial matching using aggregation
//	public List<UserDetails> partialMatch(String term){
//		AggregationOperation match = Aggregation.match(Criteria.where("newField").regex(term));
//		AggregationOperation project = Aggregation.project().and(StringOperators.Concat.valueOf("firstName").concatValueOf("lastName")).as("newField");
//		Aggregation aggregation = Aggregation.newAggregation(project, match);
//		 List<UserDetails> userDetailsObj = mongoTemplate.aggregate(aggregation, firstName, UserDetails.class).getMappedResults();
//	}
}
