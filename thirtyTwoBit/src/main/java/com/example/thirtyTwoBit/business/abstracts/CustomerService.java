package com.example.thirtyTwoBit.business.abstracts;

import java.util.List;

import com.example.thirtyTwoBit.core.utilities.BusinessException;
import com.example.thirtyTwoBit.core.utilities.result.DataResult;
import com.example.thirtyTwoBit.core.utilities.result.Result;
import com.example.thirtyTwoBit.entities.concretes.Customer;
import com.example.thirtyTwoBit.entities.dtos.CustomerListDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerPagedDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerSortedDto;
import com.example.thirtyTwoBit.requests.customerRequests.CreateCustomerRequest;



public interface CustomerService {

	Result add(CreateCustomerRequest createCustomerRequest)throws BusinessException;
	
	Result deleteCustomer (Customer customer)throws BusinessException;
	
	Result deleteCustomerById (int customerId) throws BusinessException ;
	
	DataResult<List<Customer>> getAll()throws BusinessException;
	
	DataResult<List<CustomerListDto>> getByFirstName(String firstName) throws BusinessException;
	
	DataResult<CustomerListDto> getByCustomerId(int customerId) throws BusinessException;
	
	DataResult<List<CustomerPagedDto>> getAllPagedCustomer(int pageNo, int pageSize) throws BusinessException;
	
	DataResult<List<CustomerSortedDto>> getAllSortedCustomer(int sort) throws BusinessException;

	

	

}
