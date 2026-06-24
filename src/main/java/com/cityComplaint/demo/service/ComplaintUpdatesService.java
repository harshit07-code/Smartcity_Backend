package com.cityComplaint.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.Entity.ComplaintUpdates;
import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.controller.OfficerController;
import com.cityComplaint.demo.dto.MoreComplaintDetailsResponse;
import com.cityComplaint.demo.repository.ComplaintRepository;
import com.cityComplaint.demo.repository.ComplaintUpdatesRepository;
import com.cityComplaint.demo.repository.DepartmentRepository;
import com.cityComplaint.demo.repository.OfficerRepository;

@Service
public class ComplaintUpdatesService {

    private final OfficerController officerController;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private ComplaintUpdatesRepository complaintUpdatesRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OfficerRepository officerRepository;

    ComplaintUpdatesService(OfficerController officerController) {
        this.officerController = officerController;
    }

	public ComplaintUpdates update(Long complaintId, ComplaintUpdates newData) {

		Optional<ComplaintUpdates> existingUpdate = complaintUpdatesRepository.findByComplaint_ComplaintId(complaintId);

		if (existingUpdate.isPresent()) {

			// Existing record found
			ComplaintUpdates oldData = existingUpdate.get();

			oldData.setStatus(newData.getStatus());
			oldData.setComment(newData.getComment());
			oldData.setOfficer(newData.getOfficer());
			oldData.setUpdatedAt(LocalDateTime.now());

			return complaintUpdatesRepository.save(oldData);

		} else {

			// No record found -> insert new
			newData.setUpdatedAt(LocalDateTime.now());

			return complaintUpdatesRepository.save(newData);
		}
	}

	public ResponseEntity<MoreComplaintDetailsResponse> getMoreDetails(Long complaintId) {
		Long deptId = complaintRepository.getDeptId(complaintId);

		String deptName = departmentRepository.getDeptName(deptId);
		ComplaintUpdates complaintUpdate = complaintUpdatesRepository.findByComplaintComplaintId(complaintId);
		  if(complaintUpdate == null){


				  MoreComplaintDetailsResponse response =
						  new MoreComplaintDetailsResponse();

				  response.setStatus("PENDING");
				  response.setComment("No updates available yet");
				  response.setDepartmentId(deptId);
				  response.setDepartmentName(deptName);

				  return ResponseEntity.ok(response);
			  }




		MoreComplaintDetailsResponse moreDetails = new MoreComplaintDetailsResponse();

		moreDetails.setStatus(
		        complaintUpdate.getStatus().name()
		);

		moreDetails.setUpdatedAt(complaintUpdate.getUpdatedAt());

		moreDetails.setComment(complaintUpdate.getComment());
		System.out.println(complaintUpdate.getComment());

		moreDetails.setDepartmentName(deptName);

		moreDetails.setDepartmentId(deptId);

		return ResponseEntity.ok(moreDetails);
	}

	//Below method  Return the message once a officer update any complaint.
	/*If any complaint is already in complaint_updates table then it modifies the data
	Otherwise it creates a new record for a new updated complaint*/

	public String updateDetails(Long complaintId, Long officerId, ComplaintUpdates complaintUpdates) {
		
		ComplaintUpdates complaintUpdate =  complaintUpdatesRepository.findByComplaintComplaintId(complaintId);
		
		
		 // Fetch officer from DB
	    Officer officer =
	            officerRepository.findById(officerId)
	            .orElseThrow(() ->
	                    new RuntimeException("Officer not found"));
	    
		if(complaintUpdate!=null) {
		complaintUpdate.setStatus(complaintUpdates.getStatus());
		complaintUpdate.setUpdatedAt(complaintUpdates.getUpdatedAt());
		complaintUpdate.setComment(complaintUpdates.getComment());
		
		 

	    
	    complaintUpdate.setOfficer(officer);
	    complaintUpdatesRepository.save(complaintUpdate);
		
		return "Updated successfully ";
		
		}
		
		ComplaintUpdates newComplaintUpdate = new ComplaintUpdates();
		  Complaint complaint =
		            complaintRepository.findById(complaintId)
		            .orElseThrow(() ->
		                    new RuntimeException("Complaint not found"));
		  
		  newComplaintUpdate.setStatus(complaintUpdates.getStatus());
		  newComplaintUpdate.setComment(complaintUpdates.getComment());
		  newComplaintUpdate.setUpdatedAt(complaintUpdates.getUpdatedAt());
		  newComplaintUpdate.setComplaint(complaint);
		  newComplaintUpdate.setOfficer(officer);
		  complaintUpdatesRepository.save(newComplaintUpdate);
		  return "Successfully updates are created ";
	}
}
