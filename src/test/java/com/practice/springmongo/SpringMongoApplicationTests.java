package com.practice.springmongo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.practice.springmongo.controller.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringMongoApplicationTests {

	@Autowired
	private UserController userController;
	@Test
	  public void contextLoads() {
	   // Assertions.assertThat(userController).isNot(null);
	  }

}
