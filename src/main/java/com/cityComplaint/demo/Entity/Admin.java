package com.cityComplaint.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	/*
	 * public Admin(String adminId, String name, @Email String email, String
	 * password) { super(); this.adminId = adminId; this.name = name; this.email =
	 * email; this.password = password; }
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	private String name;
	@Email
	private String email;
	private String password;
	
	

	/*
	 * public String getAdminId() { return adminId; } public void setAdminId(String
	 * adminId) { this.adminId = adminId; } public String getName() { return name; }
	 * public void setName(String name) { this.name = name; } public String
	 * getEmail() { return email; } public void setEmail(String email) { this.email
	 * = email; } public String getPassword() { return password; } public void
	 * setPassword(String password) { this.password = password; }
	 */
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
