package com.example.thirtyTwoBit.requests.customerRequests;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

	@NotNull	
	private String firstName;
	
	@NotNull	
	private String lastName;	
	
	@NotNull
	private String eMail;		
	
	@NotNull
	@Size(min = 8)
	private String password;	
	
	
}
