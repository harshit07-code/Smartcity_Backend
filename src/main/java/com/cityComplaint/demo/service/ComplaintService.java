package com.cityComplaint.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.dto.ComplaintDetailsDto;
import com.cityComplaint.demo.dto.MoreComplaintDetailsResponse;
import com.cityComplaint.demo.repository.ComplaintRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	
	
	public Complaint complaintRegistration(Complaint complaint){

		return complaintRepository.save(complaint);
	}

	
	//this method is called from userController to get the complaints.

	public List<ComplaintDetailsDto> getComplaintDetails(Long userId) {
		// 1️⃣  Fetch the raw Complaint entities
		List<Complaint> complaints = complaintRepository.findByUserUserId(userId);
		// 2️⃣  Prepare the list that will hold the DTOs
		List<ComplaintDetailsDto> dtoList = new ArrayList<>();
		// 3️⃣  Convert each Complaint → ComplaintDetailsDto
		for (Complaint c : complaints) {
			ComplaintDetailsDto dto = new ComplaintDetailsDto();
			dto.setComplaintId(c.getComplaintId());
			dto.setCreatedAt(c.getCreatedAt());
			dto.setType(c.getType() != null ? c.getType().toString() : null);
			dto.setOriginalDescription(c.getOriginalDescription());
			dto.setSummarizedDescription(c.getSummarizedDescription());
			dto.setPredictedDepartment(c.getPredictedDepartment());
			dto.setImageUrl(c.getImageUrl());
			dto.setLocation(c.getLocation());
			// ── optional user id ──
			if (c.getUser() != null) {
				dto.setUserId(c.getUser().getUserId());
			}
			// ── officer id (required by UI) ──
			if (c.getOfficer() != null) {
				dto.setOfficerId(c.getOfficer().getOfficerId());
			} else {
				// No officer assigned yet – keep it null
				dto.setOfficerId(null);
			}
			// 4️⃣  Add the populated DTO to the result list
			dtoList.add(dto);
		}
		// 5️⃣  Return the fully‑built list
		return dtoList;
	}

}
