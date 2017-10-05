package com.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.models.ErrorMessage;
import com.rest.models.TestExecution;
import com.rest.services.TestExecutionService;

@CrossOrigin(origins = "*")
@RestController
public class TestExecutionController {
	
	@Autowired
	private TestExecutionService testExecutionService;

	/*
	 * 
	 * Test Plans REST APIs
	 */
	
	@RequestMapping(value = "/addexecution", method = RequestMethod.POST)
	public ResponseEntity<?> addNewExecution(@RequestBody TestExecution testExecution) {

		try {
			testExecutionService.saveTestExecution(testExecution);
			return new ResponseEntity<>(testExecutionService.getExecution(),
					HttpStatus.OK);	
			
		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/getexecution", method = RequestMethod.GET)
	public ResponseEntity<?> getexecution() {

		try {
			return new ResponseEntity<>(testExecutionService.getExecution(),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/deleteexecution/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExecution(@PathVariable("id") int id) {

		try {
			testExecutionService.deleteTestExecution(id);
			ErrorMessage error = new ErrorMessage();
			error.setMessage("Plan deleted successfully !");
			return new ResponseEntity<>(error, HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getexecutionbyscriptid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExecutionByCaseId(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testExecutionService.getExecutionByScriptId(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}



	
}
