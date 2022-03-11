package com.practice.springmongo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.practice.springmongo.repository.UserRepository;
import com.practice.springmongo.model.UserDetails;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository dao;
	
	@Mock
	UserDetails userDetails;
	@Test
	void testSave() {
		ArgumentCaptor<UserDetails> captor = ArgumentCaptor.forClass(UserDetails.class);
		userService.save(new UserDetails(10,"demoFirstName", "demoLastName", "demoEmail",278734L,73877L,"demoProfession"));
        verify(dao).save(captor.capture());
        Assertions.assertEquals(captor.getValue().getFirstName(), "demoFirstName");
	}

	@Test
	void testSearchDetails() {
		UserDetails user=new UserDetails(10,"demoFirstName", "demoLastName", "demoEmail",278734L,73877L,"demoProfession");
		UserDetails user2=new UserDetails(20,"demoFirstName", "demoLastName", "demoEmail",278734L,73877L,"demoProfession");
		UserDetails user3=new UserDetails(30,"demoFirstName", "demoLastName", "demoEmail3",278734L,73877L,"demoProfession");
		userService.save(user);
		userService.save(user2);
		userService.save(user3);
		
		Mockito.when(dao.fullTextSearch("demoEmail"))
	      .thenReturn(Arrays.asList(user,user2));
		String searchText = "demoEmail";
	    List<UserDetails> found = userService.searchDetails(searchText);
	    List<UserDetails> notFound=userService.searchDetails("dummyEmail");
	    assertThat(notFound.size()).isEqualTo(0);
	    assertThat(found.size()).isEqualTo(2);
	      System.out.println("id of search result--->"+found);
	      System.err.println("when no result found "+notFound);
//        assertNotNull(userDetails.getId());
    }

	@Test
	void testFetchAllUsers() {
		List<UserDetails> list=new ArrayList<UserDetails>();
		UserDetails user1=new UserDetails(11,"ServiceTestFirstName1","ServiceTestLastName1","ServiceTestEmail1",1111111L,12111L,"ServiceTestProfession1");
		UserDetails user2=new UserDetails(22,"ServiceTestFirstName2","ServiceTestLastName2","ServiceTestEmail2",222222L,232111L,"ServiceTestProfession2");
		UserDetails user3=new UserDetails(33,"ServiceTestFirstName3","ServiceTestLastName3","ServiceTestEmail3",3333333L,32111L,"ServiceTestProfession3");
		list.add(user1);
		list.add(user2);
		list.add(user3);
		when(dao.find()).thenReturn(list);
		
		//
		List<UserDetails>userList=userService.fetchAllUsers();
		 assertEquals(3, userList.size());
	        verify(dao, times(1)).find();
	
	}

}
