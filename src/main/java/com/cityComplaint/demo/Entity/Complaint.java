package com.cityComplaint.demo.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {

	/*
	 * //Constructor public Complaint(String complaintId, String userId, String
	 * deptId, String title, String description, String imageURL, String status,
	 * String priority, String location, LocalDateTime createdAt) { super();
	 * this.complaintId = complaintId; this.userId = userId; this.deptId = deptId;
	 * this.title = title; this.description = description; this.imageURL = imageURL;
	 * this.status = status; this.priority = priority; this.location = location;
	 * this.createdAt = createdAt; }
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complaintId;

	@CreationTimestamp
	LocalDateTime createdAt;

	public enum Type {
		Low, Medium, Critical
	}

	@Enumerated(EnumType.STRING)
	private Type type;

	@Column(length = 5000)
	private String originalDescription;

	private String summarizedDescription;

	private String predictedDepartment;

	private String imageUrl;

	private String location;

	@JsonBackReference(value = "user-complaints")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "officer_id")
	private Officer officer;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	@JsonIgnore
	@OneToMany(mappedBy = "complaint")
	private List<UserComplaint> userComplaints;

	@JsonIgnore
	@OneToMany(mappedBy = "complaint")
	private List<ComplaintUpdates> complaintUpdates;

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", createdAt=" + createdAt + ", type=" + type
				+ ", originalDescription=" + originalDescription + ", summarizedDescription=" + summarizedDescription
				+ ", predictedDepartment=" + predictedDepartment + ", imageUrl=" + imageUrl + ", location=" + location
				+ ", user=" + user + ", officer=" + officer + ", department=" + department + ", userComplaints="
				+ userComplaints + ", complaintUpdates=" + complaintUpdates + "]";
	}

	
}
