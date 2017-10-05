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
import com.rest.services.TestPlanService;

@CrossOrigin(origins = "*")
@RestController
public class TestPlanController {

	@Autowired
	private TestPlanService testPlanService;

	/*
	 * 
	 * Test Plans REST APIs
	 */

	@RequestMapping(value = "/addplan", method = RequestMethod.POST)
	public ResponseEntity<?> addNewPlan(@RequestBody TestPlan testPlan) {

		try {
			if (testPlanService.isPlanExist(testPlan)) {
				ErrorMessage error = new ErrorMessage();
				error.setStatus(HttpStatus.BAD_REQUEST);
				error.setMessage("Plan already exist !");

				return new ResponseEntity<ErrorMessage>(error,
						HttpStatus.BAD_REQUEST);
			} else {
				testPlanService.savePlan(testPlan);
				return new ResponseEntity<>(testPlanService.getPlans(),
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

	@RequestMapping(value = "/getplan", method = RequestMethod.GET)
	public ResponseEntity<?> getPlan() {
		

		try {
			return new ResponseEntity<>(testPlanService.getPlans(),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/getplanbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPlanById(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(testPlanService.getPlansById(id),
					HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/deleteplan/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlan(@PathVariable("id") int id) {

		try {
			testPlanService.deletePlan(id);
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

	@RequestMapping(value = "/updateplan", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePlan(@RequestBody TestPlan testPlan) {

		try {

			testPlanService.updatePlan(testPlan);
			return new ResponseEntity<>(testPlanService.getPlans(),
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
