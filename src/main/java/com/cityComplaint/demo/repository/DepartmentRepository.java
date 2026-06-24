package com.cityComplaint.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


	List<Department> findAll();

	@Query("SELECT d.deptName FROM Department d WHERE d.deptId = :deptId")
	String getDeptName(@Param("deptId") Long deptId);

	@Query("""
			SELECT d FROM Department d
			WHERE LOWER(d.deptName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	List<Department> searchByKeyword(@Param("keyword") String keyword);

//	Department findByDeptCode(String deptCode);
	Department findByDeptCodeIgnoreCase(String deptCode);
}

//	Optional<Department> findByDeptCode(String deptCode);}


