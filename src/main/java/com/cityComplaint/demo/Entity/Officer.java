package com.cityComplaint.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Officer {
	
	/*
	 * public Officer(String officerId, @NotNull String name, @Email String email,
	 * 
	 * @Pattern(regexp = "^[0-9]{10}$", message = "Must be exactly 10 digits")
	 * String phoneNo, String deptId) { super(); this.officerId = officerId;
	 * this.name = name; this.email = email; this.phoneNo = phoneNo; this.deptId =
	 * deptId; }
	 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long officerId;

@NotNull
private String name;

@Email
private String email;
@Pattern(regexp = "^[0-9]{10}$", message = "Must be exactly 10 digits")
private String phoneNo;

private String password;


@ManyToOne
@JoinColumn(name = "dept_id")
@JsonBackReference
private Department department;




@JsonIgnore
@OneToMany(mappedBy = "officer")
private List<Complaint> complaints;


@OneToMany(mappedBy="officer")
@JsonIgnore
private List<ComplaintUpdates> complaintUpdates;


@Override
public String toString() {
	return "Officer [officerId=" + officerId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
			+ ", password=" + password + ", department=" + department + ", complaintUpdates=" + complaintUpdates + "]";
}


/*
 * public String getOfficerId() { return officerId; } public void
 * setOfficerId(String officerId) { this.officerId = officerId; } public String
 * getName() { return name; } public void setName(String name) { this.name =
 * name; } public String getEmail() { return email; } public void
 * setEmail(String email) { this.email = email; } public String getPhoneNo() {
 * return phoneNo; } public void setPhoneNo(String phoneNo) { this.phoneNo =
 * phoneNo; } public String getDeptId() { return deptId; } public void
 * setDeptId(String deptId) { this.deptId = deptId; }
 */

}
