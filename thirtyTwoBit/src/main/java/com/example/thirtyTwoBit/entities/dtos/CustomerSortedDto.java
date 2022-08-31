package com.example.thirtyTwoBit.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSortedDto {
	
	private int customerId;
	
	private String firstName;

	private String lastName;

	private String email;
}
