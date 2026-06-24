package com.cityComplaint.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaint { 

//
//	@jakarta.persistence.Id

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

@ManyToOne
@JoinColumn(name="user_id")
private User user;

@ManyToOne
@JoinColumn(name="complaint_id")
private Complaint complaint;

private String priority;

private String discription;

private String  location;

@CreationTimestamp
private LocalDateTime createdAt;

private String title;

//@OneToOne
//@JoinColumn(name="dept_id")
//private Department department;

	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department department;

private String imageURL;


}
