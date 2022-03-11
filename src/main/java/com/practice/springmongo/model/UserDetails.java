package com.practice.springmongo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.*;

@Getter
@Setter
@ToString

@Document(collection="user_details")
public class UserDetails {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
	private long mobileNo;
	private long income;
	private String profession;
	public UserDetails(int id, String firstName, String lastName, String emailId, long mobileNo, long income,
			String profession) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.income = income;
		this.profession = profession;
	}
	
	

}
