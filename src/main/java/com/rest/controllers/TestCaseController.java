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
import com.rest.models.TestCase;
import com.rest.models.TestPlan;
import com.rest.services.TestCaseService;
import com.rest.services.TestPlanService;

@CrossOrigin(origins = "*")
@RestController

public class TestCaseController {

	@Autowired
	private TestCaseService testCaseService;

	/*
	 * 
	 * Test Plans REST APIs
	 */

	@RequestMapping(value = "/addcase", method = RequestMethod.POST)
	public ResponseEntity<?> addNewCase(@RequestBody TestCase testCase) {
		synchronized (this) {
		try {
			if (testCaseService.isCaseExist(testCase)) {
				ErrorMessage error = new ErrorMessage();
				error.setStatus(HttpStatus.BAD_REQUEST);
				error.setMessage("Case already exist !");

				return new ResponseEntity<ErrorMessage>(error,
						HttpStatus.BAD_REQUEST);
			} else {
				testCaseService.saveCase(testCase);
				return new ResponseEntity<>(testCaseService.getCase(),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		}

	}
	@RequestMapping(value = "/getcase", method = RequestMethod.GET)
	public ResponseEntity<?> getCase() {

		try {
			return new ResponseEntity<>(testCaseService.getCase(),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/getcasebyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCaseById(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testCaseService.getCaseById(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/getcasebyplanid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCaseByPlanId(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testCaseService.getCaseByPlanId(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/deletecase/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCase(@PathVariable("id") int id) {

		try {
			testCaseService.deleteCase(id);
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
	
	@RequestMapping(value = "/updatecase", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCase(@RequestBody TestCase testCase) {
		try {

			testCaseService.updateCase(testCase);
			return new ResponseEntity<>(testCaseService.getCase(),
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
