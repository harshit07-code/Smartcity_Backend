package com.cityComplaint.demo.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity         //Lombok Annotations 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
  //Fields 
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	@Column(unique=true)
	private String email;
	@Pattern(regexp = "^[0-9]{10}$", message = "Must be exactly 10 digits")
	private String phoneNo;
	private String password;
	private String address;
	@CreationTimestamp
	LocalDateTime createdAt;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserComplaint> usercomplaints;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Complaint> complaint;
	
	/*
	 * //Getter and setters public String getUserId() { return userId; } public void
	 * setUserId(String userId) { this.userId = userId; } public String getName() {
	 * return name; } public void setName(String name) { this.name = name; } public
	 * String getEmail() { return email; } public void setEmail(String email) {
	 * this.email = email; } public String getPhoneNo() { return phoneNo; } public
	 * void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; } public String
	 * getPassword() { return password; } public void setPassword(String password) {
	 * this.password = password; } public String getAddress() { return Address; }
	 * public void setAddress(String address) { Address = address; } public
	 * LocalDateTime getCreatedAt() { return createdAt; } public void
	 * setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	 */
	
	//ToString methods 
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", password="
				+ password + ", Address=" + address + ", createdAt=" + createdAt + "]";
	}

	
	

}
