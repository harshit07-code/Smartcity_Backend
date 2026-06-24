package com.cityComplaint.demo.dto;

import java.time.LocalDateTime;

import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.Entity.ComplaintUpdates.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class MoreComplaintDetailsResponse {

	
	
	
	
	private String status;
	
	private Long departmentId;
	LocalDateTime updatedAt;
	private String departmentName;
	private String comment;
	
	
	//Getter and setters
	
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long despartmentId) {
		this.departmentId = despartmentId;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
