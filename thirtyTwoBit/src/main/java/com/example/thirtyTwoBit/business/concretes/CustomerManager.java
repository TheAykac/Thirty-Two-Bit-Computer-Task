package com.example.thirtyTwoBit.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.thirtyTwoBit.business.abstracts.CustomerService;
import com.example.thirtyTwoBit.core.utilities.BusinessException;
import com.example.thirtyTwoBit.core.utilities.exceptions.CustomerNotFounException;
import com.example.thirtyTwoBit.core.utilities.mapping.ModelMapperService;
import com.example.thirtyTwoBit.core.utilities.messages.BusinessMessages;
import com.example.thirtyTwoBit.core.utilities.result.DataResult;
import com.example.thirtyTwoBit.core.utilities.result.Result;
import com.example.thirtyTwoBit.core.utilities.result.SuccessDataResult;
import com.example.thirtyTwoBit.core.utilities.result.SuccessResult;
import com.example.thirtyTwoBit.dataAccess.CustomerDao;
import com.example.thirtyTwoBit.entities.concretes.Customer;
import com.example.thirtyTwoBit.entities.dtos.CustomerDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerPagedDto;
import com.example.thirtyTwoBit.entities.dtos.CustomerSortedDto;
import com.example.thirtyTwoBit.requests.customerRequests.CreateCustomerRequest;

import lombok.extern.slf4j.Slf4j;

// loglama gelecek

@Slf4j
@Service
public class CustomerManager implements CustomerService {

	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
		super();
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) throws BusinessException { // for adding this method

		checExistsBySave(createCustomerRequest.getEMail()); // Checking if the email is registered in the database

		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		this.customerDao.save(customer); // A relationship is established between the customer class and the
											// CreateCustomerRequest class.
		log.info(BusinessMessages.LogMessages.ADD_TO_DATABASE);
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);

	}

	@Override
	public Result deleteCustomer(Customer customer) throws BusinessException {
		checkExistsById(customer.getCustomerId());
		checExistsByFirstName(customer.getFirstName());
		checExistsByLastName(customer.getLastName());
		checExistsByEmail(customer.getEmail());

		this.customerDao.delete(customer);
		log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE);
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + customer.getCustomerId());

	}

	@Override
	public Result deleteCustomerById(int customerId) throws BusinessException {
		checkExistsById(customerId); // Checking if the customerId is registered in the database
		this.customerDao.deleteById(customerId);
		log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE);
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + customerId);
	}

	@Override
	public DataResult<List<Customer>> getAll() throws BusinessException {

		log.info(BusinessMessages.LogMessages.ALL_LIST);
		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(),
				BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);

	}

	@Override
	public DataResult<List<CustomerDto>> getByFirstName(String firstName) throws BusinessException {

		checExistsByFirstName(firstName); // Checking if the firstName is registered in the database

		List<Customer> customers = this.customerDao.getByFirstName(firstName); // If there is from the name we entered,
																				// the data of that name comes.
		List<CustomerDto> customerListDtos = customers.stream()
				.map(customer -> this.modelMapperService.forDto().map(customer, CustomerDto.class))
				.collect(Collectors.toList()); // A relationship is established between the customer class and the
												// CustomerListDto class.
		// The CustomerListDto class contains the data we want to appear.
		log.info(BusinessMessages.LogMessages.FIRST_NAME_LIST);
		return new SuccessDataResult<List<CustomerDto>>(customerListDtos,
				BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	@Override
	public DataResult<CustomerDto> getByCustomerId(int customerId) throws BusinessException {
		checkExistsById(customerId); // Checking if the customerId is registered in the database

		Customer customer = this.customerDao.getById(customerId); // If there is from the customerId we entered, the
																	// data of that name comes.

		CustomerDto result = this.modelMapperService.forDto().map(customer, CustomerDto.class);// A relationship is
																								// established between
																								// the customer class
																								// and the
																								// CustomerListDto
																								// class.
		// The CustomerListDto class contains the data we want to appear.
		log.info(BusinessMessages.LogMessages.ID_LIST);
		return new SuccessDataResult<CustomerDto>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	@Override
	public DataResult<List<CustomerPagedDto>> getAllPagedCustomer(int pageNo, int pageSize)
			throws CustomerNotFounException {

		checkIfPageNoAndPageSizeValid(pageNo, pageSize);

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Customer> customers = this.customerDao.findAll(pageable).getContent();

		List<CustomerPagedDto> result = customers.stream()
				.map(customer -> this.modelMapperService.forDto().map(customer, CustomerPagedDto.class))
				.collect(Collectors.toList());
		log.info(BusinessMessages.LogMessages.PAGED_LIST);
		return new SuccessDataResult<>(result, BusinessMessages.CustomerMessages.PAGE_NO_OR_PAGE_SIZE_NOT_VALID);
	}

	@Override
	public DataResult<List<CustomerSortedDto>> getAllSortedCustomer(int sort) throws BusinessException {
		Sort sortList = selectSortedType(sort);
		List<Customer> customers = this.customerDao.findAll(sortList);
		List<CustomerSortedDto> customerSortedDtos = customers.stream()
				.map(customer -> this.modelMapperService.forDto().map(customer, CustomerSortedDto.class))
				.collect(Collectors.toList());
		log.info(BusinessMessages.LogMessages.SORT_LIST);
		return new SuccessDataResult<List<CustomerSortedDto>>(customerSortedDtos,
				BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	private void checkExistsById(int customerId) throws BusinessException { // Checking if the customerId is registered
																			// in the database
		log.info(BusinessMessages.LogMessages.CHECKING_DB);
		if (!this.customerDao.existsById(customerId)) {
			throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_ID_NOT_FOUND + customerId);
		}

	}

	private void checExistsBySave(String email) throws BusinessException { // Checking if the email is registered in the
																			// database
		log.info(BusinessMessages.LogMessages.CHECKING_DB);
		if (this.customerDao.existsByEmail(email)) {
			throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_NOT_SEVED + email);
		}
	}

	private void checExistsByFirstName(String firstName) throws BusinessException { // Checking if the firstName is
																					// registered in the database
		log.info(BusinessMessages.LogMessages.CHECKING_DB);
		if (!this.customerDao.existsByFirstName(firstName)) {
			throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_FIRST_NAME_NOT_FOUND + firstName);
		}
	}

	private void checExistsByLastName(String lastName) throws BusinessException { // Checking if the firstName is
																					// registered in the database
		log.info(BusinessMessages.LogMessages.CHECKING_DB);
		if (!this.customerDao.existsByLastName(lastName)) {
			throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_LAST_NAME_NOT_FOUND + lastName);
		}
	}

	public Result checExistsByEmail(String email) throws BusinessException { // Checking if the firstName is registered in
																			// the database
		log.info(BusinessMessages.LogMessages.CHECKING_DB);
		if (!this.customerDao.existsByLastName(email)) {
			throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_EMAIL_NAME_NOT_FOUND + email);
		}
		return new SuccessResult("skd??las");
	}

	private void checkIfPageNoAndPageSizeValid(int pageNo, int pageSize) throws CustomerNotFounException { // Checking
																											// if the
																											// pageNo
																											// and
																											// pageSize
		log.info(BusinessMessages.LogMessages.CHECKING_PAGE);
		if (pageNo <= 0 || pageSize <= 0) {
			throw new CustomerNotFounException(BusinessMessages.CustomerMessages.PAGE_NO_OR_PAGE_SIZE_NOT_VALID);
		}
	}

	Sort selectSortedType(int sort) throws BusinessException {// The method for sorting operation
		if (sort == 1 || sort == 0) {
			if (sort == 1) {
				return Sort.by(Sort.Direction.ASC, "firstName");
			} else if (sort == 0) {
				return Sort.by(Sort.Direction.DESC, "firstName");
			} else {
				return Sort.by(Sort.Direction.DESC, "firstName");
			}
		} else {
			throw new BusinessException(BusinessMessages.CustomerMessages.SORT_NUMBER_NOT_FOUD);
		}

	}
}
