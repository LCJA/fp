package com.fp.exception;


public class ApplicationException extends BaseException{	

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ApplicationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	/**
	 * @param cause
	 * @param errorCode
	 * @param errorMessage
	 */
	public ApplicationException(Throwable cause, String errorCode,
			String errorMessage) {
		super(cause, errorCode, errorMessage);
	}

	/**
	 * @param cause
	 * @param errorCode
	 */
	public ApplicationException(Throwable cause, String errorCode) {
		super(cause, errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -7711250613973933714L;
	
	
}
