package com.school.management.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BadRequestServiceAlertException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;
    private String message;

    public BadRequestServiceAlertException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
	

}
