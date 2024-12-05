package com.teleapps.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreditCard {

	@NotNull(message = "Customer ID is required")
	private Integer custId;

	@NotNull(message = "Card number is required")
	@Size(min = 16, max = 16, message = "Card number must be 16 digits")
	private String cardNumber;

	@NotNull(message = "Card type is required")
	@Size(max = 20, message = "Card type cannot exceed 20 characters")
	private String cardType = "VISA";

	@NotNull(message = "Limit amount is required")
	@DecimalMin(value = "0.00", inclusive = true, message = "Limit amount cannot be negative")
	private Double limitAmount;

	@DecimalMin(value = "0.00", inclusive = true, message = "Balance cannot be negative")
	private Double balance = 0.00; // Default value

	@NotNull(message = "Expiry date is required")
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Invalid expiry date format. Use MM/YY")
	private String expiryDate;

	@Size(max = 10, message = "Status cannot exceed 10 characters")
	private String status = "ACTIVE"; // Default value

	@NotNull(message = "Created date is required")
	private String createdDate;

	@NotNull(message = "Created by is required")
	@Size(max = 50, message = "Created by cannot exceed 50 characters")
	private String createdBy;

	@NotNull(message = "Updated date is required")
	private String updatedDate;

	@NotNull(message = "Updated by is required")
	@Size(max = 50, message = "Updated by cannot exceed 50 characters")
	private String updatedBy;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Double getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(Double limitAmount) {
		this.limitAmount = limitAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public CreditCard(@NotNull(message = "Customer ID is required") Integer custId,
			@NotNull(message = "Card number is required") @Size(min = 16, max = 16, message = "Card number must be 16 digits") String cardNumber,
			@NotNull(message = "Card type is required") @Size(max = 20, message = "Card type cannot exceed 20 characters") String cardType,
			@NotNull(message = "Limit amount is required") @DecimalMin(value = "0.00", inclusive = true, message = "Limit amount cannot be negative") Double limitAmount,
			@DecimalMin(value = "0.00", inclusive = true, message = "Balance cannot be negative") Double balance,
			@NotNull(message = "Expiry date is required") @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Invalid expiry date format. Use MM/YY") String expiryDate,
			@Size(max = 10, message = "Status cannot exceed 10 characters") String status,
			@NotNull(message = "Created date is required") String createdDate,
			@NotNull(message = "Created by is required") @Size(max = 50, message = "Created by cannot exceed 50 characters") String createdBy,
			@NotNull(message = "Updated date is required") String updatedDate,
			@NotNull(message = "Updated by is required") @Size(max = 50, message = "Updated by cannot exceed 50 characters") String updatedBy) {
		super();
		this.custId = custId;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.limitAmount = limitAmount;
		this.balance = balance;
		this.expiryDate = expiryDate;
		this.status = status;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

}
