package com.fp.exception;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1381325479896057076L;
    protected String errorCode = "errorCode.undefined";
    protected String errorMessage;    
    
    public BaseException(Throwable cause) {
		super(cause);
		if(cause instanceof BaseException){
			BaseException e = (BaseException)cause;
			this.errorMessage = e.errorMessage;
			this.errorCode = e.errorCode;
		}
    }
    
    public BaseException(Throwable cause,String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
    
    public BaseException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}  
    
    public BaseException(Throwable cause,String errorCode, String errorMessage) {
		this(cause,errorCode);
		this.errorMessage = errorMessage;
	}
    
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
