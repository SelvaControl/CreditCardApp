package com.teleapps.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {

	@NotNull(message = "Customer ID is required")
	@Digits(integer = 4, fraction = 0, message = "Customer ID must be exactly 4 digits")
	private int customerId;

	@NotNull(message = "First Name is required")
	@Size(min = 3, max = 50, message = "First Name should be between 3 and 50 characters")
	private String firstName;

	@Size(min = 1, max = 50, message = "Last Name should be between 1 and 50 characters")
	private String lastName;

	@Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
	private Long phoneNumber;

	@NotNull(message = "Email ID is required")
	@Email(message = "Email ID should be valid")
	private String emailId;

	@Size(min = 5, max = 255, message = "Address should be between 5 and 255 characters")
	private String address;

	private String createdDate;

	@NotNull(message = "Created By is required")
	@Size(min = 2, max = 50, message = "Created By should be between 4 and 50 characters")
	private String createdBy;

	private String updatedDate;

	@NotNull(message = "Updated By is required")
	@Size(min = 2, max = 50, message = "Updated By should be between 2 and 50 characters")
	private String updatedBy;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Customer(
			@NotNull(message = "Customer ID is required") @Digits(integer = 4, fraction = 0, message = "Customer ID must be exactly 4 digits") int customerId,
			@NotNull(message = "First Name is required") @Size(min = 3, max = 50, message = "First Name should be between 3 and 50 characters") String firstName,
			@Size(min = 1, max = 50, message = "Last Name should be between 1 and 50 characters") String lastName,
			@Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits") Long phoneNumber,
			@NotNull(message = "Email ID is required") @Email(message = "Email ID should be valid") String emailId,
			@Size(min = 5, max = 255, message = "Address should be between 5 and 255 characters") String address,
			String createdDate,
			@NotNull(message = "Created By is required") @Size(min = 2, max = 50, message = "Created By should be between 4 and 50 characters") String createdBy,
			String updatedDate,
			@NotNull(message = "Updated By is required") @Size(min = 2, max = 50, message = "Updated By should be between 2 and 50 characters") String updatedBy) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.address = address;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

}
