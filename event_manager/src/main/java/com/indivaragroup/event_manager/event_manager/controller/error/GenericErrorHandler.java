package com.indivaragroup.event_manager.event_manager.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.indivaragroup.event_manager.event_manager.dto.GenericAppError;
import com.indivaragroup.event_manager.event_manager.exception.DataNotFoundException;

@RestControllerAdvice
public class GenericErrorHandler {

	@ExceptionHandler(DataNotFoundException.class)
	@RequestMapping(produces="application/json")
	public ResponseEntity<GenericAppError> onNotFound(DataNotFoundException e){
		GenericAppError errObj = new GenericAppError();
		errObj.setDescription(e.toString());
		errObj.setMessage(e.getMessage());
		return new ResponseEntity<>(errObj, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@RequestMapping(produces="application/json")
	public ResponseEntity<GenericAppError> onUnexpected(Exception e){
		GenericAppError errObj = new GenericAppError();
		errObj.setDescription(e.toString());
		errObj.setMessage(e.getMessage());
		return new ResponseEntity<>(errObj, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
