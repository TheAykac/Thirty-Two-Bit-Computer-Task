package com.example.thirtyTwoBit.entities.concretes;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")

public class Customer {

	@Id
	@GeneratedValue
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="first_name")
	private String firstName;
	
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
}
