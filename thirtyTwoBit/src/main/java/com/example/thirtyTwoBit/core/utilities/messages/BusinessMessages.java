package com.example.thirtyTwoBit.core.utilities.messages;

public class BusinessMessages {

	public class GlobalMessages {

		public static final String DATA_LISTED_SUCCESSFULLY = "Data Listed Successfully";
		public static final String DATA_ADDED_SUCCESSFULLY = "Data Added Successfully";
		public static final String DATA_ADDED_ERROR = "Data Did Not Add ";
		public static final String DATA_UPDATED_SUCCESSFULLY = "Data updated Successfully, dataId: ";
		public static final String DATA_DELETED_SUCCESSFULLY = "Data deleted successfully, dataId: ";
		public static final String DATA_BROUGHT_SUCCESSFULLY = "Data Brought Successfully, dataId: ";
		public static final String DATA_DELETE_ERROR = "Data Did Not Delete ";

	}

	public class CustomerMessages {

		public static final String CUSTOMER_ID_NOT_FOUND = "Customer Service ID not exists, customerId:  ";
		public static final String CUSTOMER_NOT_DELETE = "Cutomer Service Not Deleted";
		public static final String CUSTOMER_NOT_SEVED ="Could not add customer email: ";
		public static final String CUSTOMER_FIRST_NAME_NOT_FOUND = "Customer Service FIRST NAME not exists, First Name :";
		public static final String CUSTOMER_LAST_NAME_NOT_FOUND = "Customer Service LAST NAME not exists, Last Name :";
		public static final String CUSTOMER_EMAIL_NAME_NOT_FOUND = "Customer Service EMAIL  not exists, EMAIL :";
		public static final String PAGE_NO_OR_PAGE_SIZE_NOT_VALID = "Page No or Page Size value not valid.";
		public static final String SORT_NUMBER_NOT_FOUD = "Please eneter 1 or 0 ";
	}

	public class SecurityMessages{
		
		public static final String CUSTOMER_ROLE_NOT_APPROPRIATE = "Customer Role Is Not Appoprlate";
	}
	
	public class LogMessages{
		
		public static final String ADD_OPERATINON_WORK = "The Add Operation Worked";
		public static final String ADD_OPERATINON_NOT_WORK = "The Add Operation Did Not Work";
		public static final String DELETE_OPERATINON_WORK = "The Delete Operation Worked";
		public static final String DELETE_OPERATINON_NOT_WORK = "The Delete Operation Did Not Work";
		public static final String GET_LIST_WORKED = "The Get List Operation Worked";
		public static final String GET_LIST_NOT_WORK = "The Get List Operation Did Not Work";
		public static final String SYSTEM_STARTED ="System Is Started";
		public static final String ADD_TO_DATABASE ="Saved New Customer to The Database";
		public static final String DELETE_FROM_DATABASE ="Deleted From  Database";
		public static final String ALL_LIST ="Fetching All Customer";
		public static final String FIRST_NAME_LIST ="Fetching By First Name";
		public static final String ID_LIST ="Fetching By Customer ID";
		public static final String PAGED_LIST ="Paged Being Done";
		public static final String SORT_LIST ="Sorted Being Done";
		public static final String CHECKING_DB="Database Checking";
		public static final String CHECKING_PAGE="Page No and Page Size Checking";
		
	}
}
