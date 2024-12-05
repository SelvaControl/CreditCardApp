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

import com.teleapps.model.CreditCard;

@Repository
public class CreditCardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(CreditCardDao.class);

	public int saveCreditCard(CreditCard creditcard) {
		try {
			String createdDate = getDate();
			if (createdDate == null) {
				return 0;
			} else {
				String sql = "INSERT INTO Card (cust_id, card_number, card_type, limit_amount, balance, expiry_date, status, created_date, created_by, updated_date, updated_by)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, COALESCE(NULLIF(?, ''), ?), ?, COALESCE(NULLIF(?, ''), ?), ?)";

				int update = jdbcTemplate.update(sql, creditcard.getCustId(), creditcard.getCardNumber(),
						creditcard.getCardType(), creditcard.getLimitAmount(), creditcard.getBalance(),
						creditcard.getExpiryDate(), creditcard.getStatus(), creditcard.getCreatedDate(), createdDate,
						creditcard.getCreatedBy(), creditcard.getUpdatedDate(), createdDate, creditcard.getUpdatedBy());

				return update;
			}

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving creditcard: {}", e.getMessage(), e);
			return 0;
		}

	}

	private String getDate() {
		try {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return myDateObj.format(myFormatObj);
		} catch (Exception e) {
			logger.error("Unexpected exception occurred while saving creditcard: {}", e.getMessage(), e);
			return null;
		}
	}

	public List<CreditCard> viewCardList() {
		try {
			String sql = "SELECT * FROM card";
			return jdbcTemplate.query(sql,
					(rs, rowNum) -> new CreditCard(rs.getInt("cust_id"), rs.getString("card_number"),
							rs.getString("card_type"), rs.getDouble("limit_amount"), rs.getDouble("balance"),
							rs.getString("expiry_date"), rs.getString("status"), rs.getString("created_date"),
							rs.getString("created_by"), rs.getString("updated_date"), rs.getString("updated_by")));

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while viewing creditcard: {}", e.getMessage(), e);
			return null;
		}
	}

	public int updateCreditcard(@Valid int id, CreditCard creditcard) {
		try {
			String createdDate = getDate();
			if (createdDate == null) {
				return 0;
			} else {
				String sql = "UPDATE card "
						+ "SET card_type = ?, limit_amount = ?, balance = ?, expiry_date = ?, status = ?, updated_date = ?, updated_by = ? "
						+ "WHERE card_number = ?";

				return jdbcTemplate.update(sql, creditcard.getCardType(), creditcard.getLimitAmount(),
						creditcard.getBalance(), creditcard.getExpiryDate(), creditcard.getStatus(), createdDate,
						creditcard.getUpdatedBy(), id);
			}

		} catch (Exception e) {
			logger.error("Unexpected exception occurred while updating creditcard: {}", e.getMessage(), e);
			return 0;
		}
	}
	
	public int deleteCreditcardById(@Valid int id) {
		try {
			String sql = "DELETE FROM card WHERE card_number = ?";
			int rowsAffected = jdbcTemplate.update(sql, id);
			return rowsAffected;
		} catch (Exception e) {
			logger.error("Unexpected exception occurred while updating creditcard: {}", e.getMessage(), e);
			return 0;
		}
	}

}
