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
import com.rest.models.TestPlan;
import com.rest.models.TestScript;
import com.rest.services.TestPlanService;
import com.rest.services.TestScriptService;

@CrossOrigin(origins = "*")
@RestController
public class TestScriptController {

	@Autowired
	private TestScriptService testScriptService;

	/*
	 * 
	 * Test Plans REST APIs
	 */

	@RequestMapping(value = "/addscript", method = RequestMethod.POST)
	public ResponseEntity<?> addNewScript(@RequestBody TestScript testScript) {

		try {
			if (testScriptService.isScriptExist(testScript)) {
				ErrorMessage error = new ErrorMessage();
				error.setStatus(HttpStatus.BAD_REQUEST);
				error.setMessage("Script already exist !");

				return new ResponseEntity<ErrorMessage>(error,
						HttpStatus.BAD_REQUEST);
			} else {
				testScriptService.saveScript(testScript);
				return new ResponseEntity<>(testScriptService.getScript(),
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
	
	@RequestMapping(value = "/getscript", method = RequestMethod.GET)
	public ResponseEntity<?> getScript() {

		try {
			return new ResponseEntity<>(testScriptService.getScript(),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/getscriptbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getScriptById(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testScriptService.getScriptById(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getscriptbycaseid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getScriptByCaseId(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testScriptService.getScriptByCaseId(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/deletescript/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteScript(@PathVariable("id") int id) {

		try {
			testScriptService.deleteScript(id);
			ErrorMessage error = new ErrorMessage();
			error.setMessage("Script deleted successfully !");
			return new ResponseEntity<>(error, HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/deletedataset/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDataSet(@PathVariable("id") int id) {

		try {
			testScriptService.deleteDataSet(id);
			ErrorMessage error = new ErrorMessage();
			error.setMessage("dataset deleted successfully !");
			return new ResponseEntity<>(error, HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/updatescript", method = RequestMethod.PUT)
	public ResponseEntity<?> updateScript(@RequestBody TestScript testScript) {

		try {

			testScriptService.updateScript(testScript);
			return new ResponseEntity<>(testScriptService.getScript(),
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
