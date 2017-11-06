package com.luqili.utils.pub.json;

public class JsonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JsonException() {
        super();
    }
	public JsonException(String message) {
        super(message);
    }
	public JsonException(String message, Throwable cause) {
	    super(message, cause);
	}

}
