package com.cityComplaint.demo.repository;

import java.util.List;

import com.cityComplaint.demo.Entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.Entity.User;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {

	Officer findByEmail(String email);

	List<Officer> findByDepartmentDeptId(Long deptId);

	Officer findByOfficerId(Long officerId);

}
