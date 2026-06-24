package com.cityComplaint.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Department;
import com.cityComplaint.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Department departmentCreation(Department department) {
		
		return departmentRepository.save(department);
	}
	
	public List<Department> getAllDeptNames() {
     return departmentRepository.findAll();

}

	public Department getDepartmentByCode(String code) {

		Department department =
				departmentRepository.findByDeptCodeIgnoreCase(code);

		if(department == null) {

			department =
					departmentRepository.findByDeptCodeIgnoreCase("GENERAL");

			if(department == null) {
				throw new RuntimeException(
						"GENERAL department missing"
				);
			}
		}

		return department;
	}

		public List<Department> getAllDepartments() {
			return departmentRepository.findAll();
		}

}

