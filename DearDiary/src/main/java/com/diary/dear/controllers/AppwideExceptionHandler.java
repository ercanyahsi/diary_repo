package com.diary.dear.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppwideExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String commonException(){
		return "error";
	}
}
