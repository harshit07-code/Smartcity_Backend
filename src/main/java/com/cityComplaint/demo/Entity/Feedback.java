package com.cityComplaint.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
	
	/*
	 * public Feedback(String feedbackId, String userId, String complaintId, String
	 * rating, String comment, LocalDateTime createdAt) { super(); this.feedbackId =
	 * feedbackId; this.userId = userId; this.complaintId = complaintId; this.rating
	 * = rating; this.comment = comment; this.createdAt = createdAt; }
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedbackId;
	private Long userId;
	private Long complaintId;
	private String rating;
	private String comment;
	LocalDateTime  createdAt;
	
	
	/*
	 * public String getFeedbackId() { return feedbackId; } public void
	 * setFeedbackId(String feedbackId) { this.feedbackId = feedbackId; } public
	 * String getUserId() { return userId; } public void setUserId(String userId) {
	 * this.userId = userId; } public String getComplaintId() { return complaintId;
	 * } public void setComplaintId(String complaintId) { this.complaintId =
	 * complaintId; } public String getRating() { return rating; } public void
	 * setRating(String rating) { this.rating = rating; } public String getComment()
	 * { return comment; } public void setComment(String comment) { this.comment =
	 * comment; } public LocalDateTime getCreatedAt() { return createdAt; } public
	 * void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	 */
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", userId=" + userId + ", complaintId=" + complaintId
				+ ", rating=" + rating + ", comment=" + comment + ", createdAt=" + createdAt + "]";
	}
	

}
