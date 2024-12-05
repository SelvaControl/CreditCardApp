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

import com.teleapps.dao.CreditCardDao;
import com.teleapps.model.CreditCard;
import com.teleapps.model.ErrorResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/creditcard")
@Tag(name = "CreditCard Controller", description = "APIs for CreditCard management")
public class CreditCardController {
	private static final Logger logger = LogManager.getLogger(CreditCardController.class);

	@Autowired
	private CreditCardDao creditcarddao;

	@GetMapping("/credit-test")
	public String greet() {
		logger.info("Started CreditCard management");
		return "Started CreditCard management";
	}

	@PostMapping("/addcus")
	public ResponseEntity<Object> addCustomer(@Valid @RequestBody CreditCard creditcard) {
		try {
			logger.info("Adding creditCard Details:", creditcard);
			int result = creditcarddao.saveCreditCard(creditcard);
			if (result > 0) {
				ErrorResponse succesResponse = new ErrorResponse(HttpStatus.CREATED.value(),
						"CreditCard added successfully.", null, null, LocalDateTime.now());
				return new ResponseEntity<>(succesResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				logger.warn("Failed to add CreditCard.");
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Failed to add CreditCard.", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Error occurred while adding CreditCard: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getting-creditcardlist")
	public ResponseEntity<Object> viewCardList() {
		try {
			List<CreditCard> creditcard = creditcarddao.viewCardList();
			if (creditcard.isEmpty() || creditcard == null) {
				logger.warn("No CreditCard list found");
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(),
						"No CreditCard list found", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
			} else {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(), "CreditCard list found",
						creditcard, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);

			}

		} catch (Exception e) {
			logger.error("Error occurred while viewing CreditCard list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update-creditcard/{id}")
	public ResponseEntity<Object> updateCustomerById(@Valid @PathVariable int id, @RequestBody CreditCard creditcard) {
		try {
			int responseMessage = creditcarddao.updateCreditcard(id, creditcard);
			if (responseMessage > 0) {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(),
						"CreditCard updated successfully", creditcard, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
			} else {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(),
						"CreditCard with ID " + id + " not found", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error occurred while viewing CreditCard list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-creditcard/{id}")
	public ResponseEntity<Object> deleteCustomer(@Valid @PathVariable int id) {
		try {
			int responseMessage = creditcarddao.deleteCreditcardById(id);
			if (responseMessage > 0) {
				ErrorResponse successResponse = new ErrorResponse(HttpStatus.OK.value(),
						"CreditCard Deleted successfully", null, null, LocalDateTime.now());
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
			} else {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(),
						"CreditCard with ID " + id + " not found", null, null, LocalDateTime.now());
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Error occurred while viewing CreditCard list: {}", e.getMessage(), e);
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An error occurred while processing your request.", null, null, LocalDateTime.now());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
