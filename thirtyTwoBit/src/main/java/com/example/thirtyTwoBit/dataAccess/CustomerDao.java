package com.example.thirtyTwoBit.dataAccess;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thirtyTwoBit.core.utilities.result.Result;
import com.example.thirtyTwoBit.entities.concretes.Customer;



public interface CustomerDao extends  JpaRepository<Customer, Integer>{

	boolean existsById(int customerId);
	
	boolean existsByEmail(String email);
	
	boolean existsByFirstName(String firstName);
	
	boolean existsByLastName(String lastName);
	
	Result deleteByEmail(String email);
	
	List<Customer> getByFirstName(String firstName);
	
	
}
