package com.cityComplaint.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	/*
	 * public Department(String deptId, String deptName, String description) {
	 * super(); this.deptId = deptId; this.deptName = deptName; this.description =
	 * description; }
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptId;
	
	private String deptName;

	@Column(name = "dept_code")
	private String deptCode;

	@JsonManagedReference
	@OneToMany(mappedBy = "department")
	private List<Officer> officers;
	
	@OneToMany(mappedBy = "department")
	private List<Complaint> complaints;
	
	
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
	
	
	/*
	 * public String getDeptId() { return deptId; } public void setDeptId(String
	 * deptId) { this.deptId = deptId; } public String getDeptName() { return
	 * deptName; } public void setDeptName(String deptName) { this.deptName =
	 * deptName; } public String getDescription() { return description; } public
	 * void setDescription(String description) { this.description = description; }
	 */
	
}
