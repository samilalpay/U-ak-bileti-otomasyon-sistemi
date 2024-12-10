package com.samilemir.exception;

public class BaseException extends RuntimeException {
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepErrorMessage());
	}

}
