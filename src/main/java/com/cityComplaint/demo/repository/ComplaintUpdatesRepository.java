package com.cityComplaint.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.ComplaintUpdates;

@Repository
public interface ComplaintUpdatesRepository extends JpaRepository<ComplaintUpdates, Long> {
	

 Optional<ComplaintUpdates> findByComplaint_ComplaintId(Long complaintId);

 

 ComplaintUpdates findByComplaintComplaintId(Long complaintId);

}

