package com.cityComplaint.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.dto.ComplaintDetailsDto;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

	List<Complaint> findByUserUserId(Long userId);

	 @Query("SELECT c.department.deptId FROM Complaint c WHERE c.complaintId = :complaintId")
	    Long getDeptId(@Param("complaintId") Long complaintId);

	 List<Complaint> findByOfficerOfficerId(Long officerId);


	@Query("SELECT c FROM Complaint c " +
			"WHERE c.officer.officerId = :officerId " +
			"AND NOT EXISTS (" +
			"   SELECT u FROM ComplaintUpdates u " +
			"   WHERE u.complaint.complaintId = c.complaintId " +
			"   AND u.status = com.cityComplaint.demo.Entity.ComplaintUpdates$Status.RESOLVED" +
			")")
	List<Complaint> findByOfficerOfficerIdAndNoResolvedUpdate(@Param("officerId") Long officerId);
	// -----------------------------------------------------------------
	//  NEW: Complaints whose **latest** update is IN_PROGRESS
	// -----------------------------------------------------------------
	@Query("SELECT c FROM Complaint c " +
			"WHERE c.officer.officerId = :officerId " +
			"AND EXISTS (" +
			"   SELECT u FROM ComplaintUpdates u " +
			"   WHERE u.complaint.complaintId = c.complaintId " +
			"   AND u.status = com.cityComplaint.demo.Entity.ComplaintUpdates$Status.IN_PROGRESS " +
			"   AND u.updatedAt = (" +
			"       SELECT MAX(u2.updatedAt) FROM ComplaintUpdates u2 " +
			"       WHERE u2.complaint.complaintId = c.complaintId" +
			"   )" +
			")")
	List<Complaint> findByOfficerOfficerIdAndLatestStatusInProgress(@Param("officerId") Long officerId);



	@Query("SELECT DISTINCT c FROM Complaint c " +
			"JOIN c.complaintUpdates u " +
			"WHERE c.officer.officerId = :officerId " +
			"AND u.status = com.cityComplaint.demo.Entity.ComplaintUpdates$Status.RESOLVED")
	List<Complaint> findByOfficerOfficerIdAndResolved(@Param("officerId") Long officerId);

    long countByOfficerOfficerId(Long officerId);
}
	


