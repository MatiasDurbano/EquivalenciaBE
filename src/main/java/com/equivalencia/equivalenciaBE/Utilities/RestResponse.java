package com.equivalencia.equivalenciaBE.Utilities;

public class RestResponse {

	
	private Integer responseCode;
	private String message;
	
	public RestResponse(Integer responseCode) {
		super();
		this.responseCode=responseCode;
	}
	
	public RestResponse(Integer responseCode,String message) {
		super();
		this.message=message;
		this.responseCode=responseCode;
	}
	
	public Integer getResponse() {
		return responseCode;
	}
	public void setResponse(Integer response) {
		this.responseCode = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
