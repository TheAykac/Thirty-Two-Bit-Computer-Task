package com.example.thirtyTwoBit.business.concretes;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.thirtyTwoBit.core.utilities.BusinessException;
import com.example.thirtyTwoBit.core.utilities.mapping.ModelMapperManager;
import com.example.thirtyTwoBit.core.utilities.mapping.ModelMapperService;
import com.example.thirtyTwoBit.core.utilities.result.SuccessResult;
import com.example.thirtyTwoBit.dataAccess.CustomerDao;
import com.example.thirtyTwoBit.entities.concretes.Customer;
import com.example.thirtyTwoBit.requests.customerRequests.CreateCustomerRequest;

class CustomerManagerTest {

	private CustomerManager customerManager;
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;

	@BeforeEach
	void setUp() throws Exception {
		customerDao = Mockito.mock(CustomerDao.class);
		modelMapperService = Mockito.mock(ModelMapperService.class);
		customerManager = new CustomerManager(customerDao, modelMapperService);

	}

	@Test
	public void addCustomerTest() throws BusinessException {

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setEmail("farukomeraykac@gmail.com");
		customer.setFirstName("Ömer Faruk");
		customer.setLastName("Aykaç");
		customer.setPassword("Test123.");

		customerDao.save(customer);

		Assertions.assertThat(customer.getCustomerId()).isGreaterThan(0);

	}
	
	@Test
	public void deleteCustomer()throws BusinessException{
		
		
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setEmail("farukomeraykac@gmail.com");
		customer.setFirstName("Ömer Faruk");
		customer.setLastName("Aykaç");
		customer.setPassword("Test123.");
		
		customerDao.delete(customer);
		
		
	}
	
	@Test
	public void deleteCustomerByIdTest() throws BusinessException{
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setEmail("farukomeraykac@gmail.com");
		customer.setFirstName("Ömer Faruk");
		customer.setLastName("Aykaç");
		customer.setPassword("Test123.");
		 
		 customerDao.deleteById(customer.getCustomerId());
	      
		
	}
	
	

	
	@Test
	public void getFirstNameListOfTest() throws BusinessException{
		
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setEmail("farukomeraykac@gmail.com");
		customer.setFirstName("Ömer Faruk");
		customer.setLastName("Aykaç");
		customer.setPassword("Test123.");
		
		List<Customer> customers = customerDao.getByFirstName(customer.getFirstName());
		
		
	}
	
	

}
