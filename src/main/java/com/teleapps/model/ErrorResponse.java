package com.teleapps.model;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private int status;
    private String message;
    private Object data;
	private List<String> errors;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message, Object data, List<String> errors, LocalDateTime timestamp) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.errors = errors;
		this.timestamp = timestamp;
	}

    public Object getData() {
  		return data;
  	}
  	public void setData(Object data) {
  		this.data = data;
  	}
	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

   
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
