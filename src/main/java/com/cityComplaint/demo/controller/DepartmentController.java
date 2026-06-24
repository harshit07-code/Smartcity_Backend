package com.cityComplaint.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.Department;
import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.repository.DepartmentRepository;
import com.cityComplaint.demo.repository.OfficerRepository;
import com.cityComplaint.demo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	
@Autowired
private DepartmentService departmentService;

//@Autowired 
//private DepartmentRepository departmentRepository;

@Autowired
private OfficerRepository officerRepository;

@Autowired
private DepartmentRepository departmentRepository;


@PostMapping("/create")
public ResponseEntity<Department> createDepartment(@RequestBody Department department){
	
	Department savedDepartment = departmentService.departmentCreation(department);
	return ResponseEntity.ok(savedDepartment);
	
}
@GetMapping("/names")
public List<Department> totalDepartment(){
	return departmentService.getAllDeptNames();
}

@GetMapping("/department/{deptId}")
public List<Officer> getOfficersByDepartment(
        @PathVariable Long deptId) {

    return officerRepository.findByDepartmentDeptId(deptId);
}



	@GetMapping("/all")
	public ResponseEntity<List<Department>> getAllDepartments() {

		return ResponseEntity.ok(
				departmentRepository.findAll()
		);
	}

}
