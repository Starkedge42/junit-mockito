package com.practice.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@EnableMongoRepositories(basePackages="package com.practice.springmongo.repository")
@SpringBootApplication
public class SpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

}
