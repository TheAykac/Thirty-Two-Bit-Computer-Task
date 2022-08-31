package com.example.thirtyTwoBit.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thirtyTwoBit.business.abstracts.CustomerService;
import com.example.thirtyTwoBit.core.utilities.BusinessException;
import com.example.thirtyTwoBit.core.utilities.messages.BusinessMessages;
import com.example.thirtyTwoBit.core.utilities.result.DataResult;
import com.example.thirtyTwoBit.core.utilities.result.ErrorDataResult;
import com.example.thirtyTwoBit.core.utilities.result.ErrorResult;
import com.example.thirtyTwoBit.core.utilities.result.Result;
import com.example.thirtyTwoBit.core.utilities.result.SuccessDataResult;
import com.example.thirtyTwoBit.core.utilities.result.SuccessResult;
import com.example.thirtyTwoBit.entities.concretes.Customer;
import com.example.thirtyTwoBit.entities.dtos.CustomerListDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerPagedDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerSortedDto;
import com.example.thirtyTwoBit.requests.customerRequests.CreateCustomerRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerControllers {

	private CustomerService customerService;

	@Autowired
	public CustomerControllers(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) throws BusinessException {
		try {
			this.customerService.add(createCustomerRequest); // Contains variables that must be entered when //
																// registering.
			log.warn("[add] "+BusinessMessages.LogMessages.ADD_OPERATINON_WORK);// Logging
			return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);

		} catch (Exception e) {
			log.warn("[add] "+BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK);// Logging
			return new ErrorResult(e.getMessage());

		}

	}

	@DeleteMapping("/deleteCustomer")
	public Result delete(@RequestBody Customer customer) throws BusinessException {
		try {
			this.customerService.deleteCustomer(customer); // Contains variables that must be entered when delete.
			log.warn("[delete] "+BusinessMessages.LogMessages.DELETE_OPERATINON_WORK);// Logging
			return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);

		} catch (Exception e) {
			log.warn("[delete] "+BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK);// Logging
			return new ErrorResult(e.getMessage());

		}

	}

	@DeleteMapping("/deleteCustomerById")
	public Result deleteCustomerById(int customerId) throws BusinessException {
		try {
			this.customerService.deleteCustomerById(customerId);
			log.warn("[deleteCustomerById] "+BusinessMessages.LogMessages.DELETE_OPERATINON_WORK);// Logging
			return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);

		} catch (Exception e) {
			log.warn("[deleteCustomerById] "+BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK);// Logging
			return new ErrorResult(e.getMessage());
		}

	}

	@GetMapping("/getAll")
	public DataResult<List<Customer>> getAll() throws BusinessException {
		try {
			log.warn("[getAll] "+BusinessMessages.LogMessages.GET_LIST_WORKED);// Logging
			return this.customerService.getAll();

		} catch (Exception e) {
			log.warn("[getAll] "+ BusinessMessages.LogMessages.GET_LIST_WORKED);// Logging
			return new ErrorDataResult<List<Customer>>(e.getMessage());
		}

	}

	@GetMapping("/getByCustomerId")
	public DataResult<CustomerListDto> getCustomerById(int customerId) throws BusinessException {
		try {
			log.warn("[getCustomerById] "+BusinessMessages.LogMessages.GET_LIST_WORKED);// Logging
			return this.customerService.getByCustomerId(customerId);

		} catch (Exception e) {

			log.warn("[getCustomerById] "+BusinessMessages.LogMessages.GET_LIST_NOT_WORK);// Logging
			return new SuccessDataResult<CustomerListDto>(e.getMessage());
		}

	}

	@GetMapping("/getByFirstName")
	public DataResult<List<CustomerListDto>> getByFirstName(String firstName) throws BusinessException {
		try {
			log.warn("[getByFirstName] "+BusinessMessages.LogMessages.GET_LIST_WORKED);// Logging
			return this.customerService.getByFirstName(firstName);

		} catch (Exception e) {

			log.warn("[getByFirstName] "+BusinessMessages.LogMessages.GET_LIST_NOT_WORK);// Logging
			return new ErrorDataResult<List<CustomerListDto>>(e.getMessage());
		}

	}

	@GetMapping("getAllPagedCustomer")
	public DataResult<List<CustomerPagedDto>> getAllPagegCustomer(int pageNo, int pageSize) throws BusinessException {

		try {
			log.warn("[getAllPagegCustomer] "+BusinessMessages.LogMessages.GET_LIST_WORKED);
			return this.customerService.getAllPagedCustomer(pageNo, pageSize);

		} catch (Exception e) {
			log.warn("[getAllPagegCustomer] "+BusinessMessages.LogMessages.GET_LIST_NOT_WORK);
			return new ErrorDataResult<List<CustomerPagedDto>>(e.getMessage());
		}
	}
	
	@GetMapping("getAllSortedCustomerFirstName")
	public DataResult<List<CustomerSortedDto>> getAllSortedCustomer ( int sort) throws BusinessException{
		try {
			log.warn("[getAllSortedCustomer] "+BusinessMessages.LogMessages.GET_LIST_WORKED);
			return this.customerService.getAllSortedCustomer(sort);
		} catch (Exception e) {
			log.warn("[getAllSortedCustomer] "+BusinessMessages.LogMessages.GET_LIST_NOT_WORK);
			return new ErrorDataResult<List<CustomerSortedDto>>(e.getMessage());
		}
	}

}

