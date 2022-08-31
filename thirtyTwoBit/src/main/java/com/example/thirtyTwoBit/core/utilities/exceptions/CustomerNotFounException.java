package com.example.thirtyTwoBit.core.utilities.exceptions;

import com.example.thirtyTwoBit.core.utilities.BusinessException;

public class CustomerNotFounException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFounException(String message) {
        super(message);
    }
}
