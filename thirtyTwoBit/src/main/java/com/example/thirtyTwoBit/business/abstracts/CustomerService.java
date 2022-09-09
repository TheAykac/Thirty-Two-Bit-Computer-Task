package com.example.thirtyTwoBit.business.abstracts;

import java.util.List;

import com.example.thirtyTwoBit.core.utilities.BusinessException;
import com.example.thirtyTwoBit.core.utilities.result.DataResult;
import com.example.thirtyTwoBit.core.utilities.result.Result;
import com.example.thirtyTwoBit.entities.concretes.Customer;
import com.example.thirtyTwoBit.entities.dtos.CustomerDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerPagedDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerSortedDto;
import com.example.thirtyTwoBit.requests.customerRequests.CreateCustomerRequest;



public interface CustomerService {
	//java doc nedir

	Result add(CreateCustomerRequest createCustomerRequest)throws BusinessException;
	
	Result deleteCustomer (Customer customer)throws BusinessException;
	
	Result deleteCustomerById (int customerId) throws BusinessException ;
	
	DataResult<List<Customer>> getAll()throws BusinessException;
	
	DataResult<List<CustomerDto>> getByFirstName(String firstName) throws BusinessException;
	
	DataResult<CustomerDto> getByCustomerId(int customerId) throws BusinessException;
	
	DataResult<List<CustomerPagedDto>> getAllPagedCustomer(int pageNo, int pageSize) throws BusinessException;
	
	DataResult<List<CustomerSortedDto>> getAllSortedCustomer(int sort) throws BusinessException;

	

	

}
