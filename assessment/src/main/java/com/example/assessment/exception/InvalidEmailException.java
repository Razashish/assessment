package com.example.assessment.exception;

public class InvalidEmailException extends RuntimeException{
private String msg = "Customer email is invalid, kindly specify a valid email";
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

}
