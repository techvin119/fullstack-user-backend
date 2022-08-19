package com.fse.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException ex){
		
		Map<String,String> errorDetails = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorDetails.put(error.getField(),error.getDefaultMessage());
		});
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST); 
	} 
	
	@ExceptionHandler(InvalidSkillRangeException.class)
	public ResponseEntity<?> handleSkillRangeException(InvalidSkillRangeException rangeEx){
		Map<String,String> skillRangeError = new HashMap<>();
		skillRangeError.put(rangeEx.getSkillField(), rangeEx.getMessage());
		return new ResponseEntity<>(skillRangeError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAssociateId.class)
	public ResponseEntity<?> handleAssociateIdException(InvalidAssociateId invalidIdEx){
		Map<String,String> invalidId = new HashMap<>();
		invalidId.put(invalidIdEx.getAssociateId(), invalidIdEx.getMessage());
		return new ResponseEntity<>(invalidId,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidUserIdExcpetion.class)
	public ResponseEntity<?> handleUserIdException(InvalidUserIdExcpetion userIdEx){
		return new ResponseEntity<>("User Id " + userIdEx.getUserId() + " not found or invalid",HttpStatus.OK);
	}

	@ExceptionHandler(UpdateProfileDaysException.class)
	public ResponseEntity<?> handleUpdateProfileDaysException(UpdateProfileDaysException daysEx){
		return new ResponseEntity<>("Update of profile should be after 10 days of adding profile or last change",HttpStatus.OK);
	}
}
