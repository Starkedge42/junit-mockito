package com.practice.springmongo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.practice.springmongo.model.UserDetails;
import com.practice.springmongo.service.UserService;



@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	 @MockBean
	  UserService userService;
	 
	  @Autowired
	  MockMvc mockMvc;
	@Test
	public void testFindAll() throws Exception{
		UserDetails user1=new UserDetails(1,"testFirstName","testFirstName","testEmail",6736348L,5555L,"testProfession");
		UserDetails user2=new UserDetails(2,"testFirstName2","testFirstName2","testEmail2",26736348L,25555L,"testProfession2");
		List<UserDetails> users=new ArrayList<>();
		users.add(user1);
		users.add(user2);
		Mockito.when(userService.fetchAllUsers()).thenReturn(users);
		 mockMvc.perform(get("/findAllUsers"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", Matchers.hasSize(2)))
	        .andExpect(jsonPath("$[0].firstName", Matchers.is("testFirstName")));
		
	}

}
