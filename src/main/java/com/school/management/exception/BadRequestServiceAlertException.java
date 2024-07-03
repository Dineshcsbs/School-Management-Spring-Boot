package com.school.management.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BadRequestServiceAlertException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestServiceAlertException(final String message) {
        super(message);
    }
}
