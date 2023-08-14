package com.example.assessment.exception;

public class CustomerNotFoundException extends RuntimeException {
	private String msg = "Customer not found";
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
