package com.teleapps.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teleapps.model.Customer;

@Repository
public class CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(CustomerDao.class);

	public int saveCustomer(Customer customer) {
		try {
			String createdDate = getDate();
			if (createdDate == null) {
				return 0;
			} else {
				String sql = "INSERT INTO CUSTOMER (customer_id, first_name, last_name, phone_number, email_id, address, created_date, created_by, updated_date, updated_by) "
						+ "VALUES (?, ?, ?, ?, ?, COALESCE(NULLIF(?, ''), ?), ?, COALESCE(NULLIF(?, ''), ?), ?)";

				int update = jdbcTemplate.update(sql, customer.getCustomerId(), customer.getFirstName(),
						customer.getLastName(), customer.getPhoneNumber(), customer.getEmailId(), customer.getAddress(),
						customer.getCreatedDate(), createdDate, customer.getCreatedBy(), customer.getUpdatedDate(),
						createdDate, customer.getUpdatedBy());

				return update;
			}

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving customer: {}", e.getMessage(), e);
			return 0;
		}

	}

	private String getDate() {
		try {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return myDateObj.format(myFormatObj);
		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving customer: {}", e.getMessage(), e);
			return null;
		}
	}

	public List<Customer> viewCustomers() {
		try {
			String sql = "SELECT * FROM CUSTOMER";
			return jdbcTemplate.query(sql,
					(rs, rowNum) -> new Customer(rs.getInt("customer_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getLong("phone_number"), rs.getString("email_id"),
							rs.getString("address"), rs.getString("created_date"), rs.getString("created_by"),
							rs.getString("updated_date"), rs.getString("updated_by")));

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving customer: {}", e.getMessage(), e);
			return null;
		}
	}

	public int updateCustomerById(@Valid int id, Customer customer) {
		try {
			String createdDate = getDate();
			if (createdDate == null) {
				return 0;
			} else {
				String sql = "UPDATE CUSTOMER "
						+ "SET first_name = ?, last_name = ?, phone_number = ?, email_id = ?, address = ?, updated_date = ?, updated_by = ? "
						+ "WHERE customer_id = ?";

				return jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(),
						customer.getPhoneNumber(), customer.getEmailId(), customer.getAddress(), createdDate,
						customer.getUpdatedBy(), id);
			}

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving customer: {}", e.getMessage(), e);
			return 0;
		}
	}

	public int deleteCustomerById(@Valid int id) {
		try {
			String sql = "DELETE FROM CUSTOMER WHERE customer_id = ?";
			int rowsAffected = jdbcTemplate.update(sql, id);
			return rowsAffected;
		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving customer: {}", e.getMessage(), e);
			return 0;
		}
	}


}
