package com.cityComplaint.demo.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.cityComplaint.demo.Entity.Complaint.Type;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ComplaintDetailsDto {

	private Long complaintId;

	
	LocalDateTime createdAt;

	private String type;

	@Column(length = 5000)
	private String originalDescription;

	private String summarizedDescription;

	private String predictedDepartment;

	private String imageUrl;

	private String location;

	private Long userId;
	
	private Long officerId;
	
	//Getter and setters

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginalDescription() {
		return originalDescription;
	}

	public void setOriginalDescription(String originalDescription) {
		this.originalDescription = originalDescription;
	}

	public String getSummarizedDescription() {
		return summarizedDescription;
	}

	public void setSummarizedDescription(String summarizedDescription) {
		this.summarizedDescription = summarizedDescription;
	}

	public String getPredictedDepartment() {
		return predictedDepartment;
	}

	public void setPredictedDepartment(String predictedDepartment) {
		this.predictedDepartment = predictedDepartment;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOfficerId() {
		return officerId;
	}

	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}
	
	
}
