package com.teleapps.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teleapps.dao.CustomerDao;
import com.teleapps.model.Customer;
import com.teleapps.model.ErrorResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Customer Controller", description = "APIs for customer management")
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerDao customerDao;

	@GetMapping("/test")
	public String greet() {
		logger.info("Started Customer Controller");
		return "Customer Controller Started";
	}

	@PostMapping("/addcus")
	public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer customer) {
		try {
			logger.info("Adding customer: {}", customer);
			int result = customerDao.saveCustomer(customer);
			if (result > 0) {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CREATED.value(),
						"Customer added successfully.", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				logger.warn("Failed to add customer.");
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Failed to add customer.", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Error occurred while adding customer: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getting-customerlist")
	public ResponseEntity<Object> viewCustomers() {
		try {
			List<Customer> customers = customerDao.viewCustomers();
			if (customers.isEmpty() || customers == null) {
				logger.warn("No customer list found");
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), "No customer list found",
						null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
			} else {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(), "Customer list found",
						customers, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);

			}

		} catch (Exception e) {
			logger.error("Error occurred while viewing customer list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update-customer/{id}")
	public ResponseEntity<Object> updateCustomerById(@Valid @PathVariable int id, @RequestBody Customer customer) {
		try {
			int responseMessage = customerDao.updateCustomerById(id, customer);
			if (responseMessage > 0) {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(),
						"Customer updated successfully", customer, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
			} else {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(),
						"Customer with ID " + id + " not found", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error occurred while viewing customer list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<Object> deleteCustomer(@Valid @PathVariable int id) {
		try {
			int responseMessage = customerDao.deleteCustomerById(id);
			if (responseMessage > 0) {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(),
						"Customer Deleted successfully", null, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
			} else {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(),
						"Customer with ID " + id + " not found", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Error occurred while viewing customer list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
