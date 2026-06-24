package com.cityComplaint.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.dto.ComplaintDetailsDto;
import com.cityComplaint.demo.repository.ComplaintRepository;

@Service
public class ComplaintDetailsDtoService {

	@Autowired
	ComplaintRepository complaintRepository;
	
	
	//Officer  Details get Method from OfficerController

	public List<ComplaintDetailsDto> getOfficerComplaintDetails(Long officerId) {

		List<Complaint> complaints = complaintRepository.findByOfficerOfficerId(officerId);

		List<ComplaintDetailsDto> response = new ArrayList<>();

		for (Complaint c : complaints) {

			ComplaintDetailsDto dto = new ComplaintDetailsDto();

			dto.setComplaintId(c.getComplaintId());

			dto.setCreatedAt(c.getCreatedAt());

			dto.setType(c.getType().toString());

			dto.setOriginalDescription(c.getOriginalDescription());

			dto.setSummarizedDescription(c.getSummarizedDescription());

			dto.setPredictedDepartment(c.getPredictedDepartment());

			dto.setImageUrl(c.getImageUrl());

			dto.setLocation(c.getLocation());

			dto.setUserId(c.getUser().getUserId());
			

			response.add(dto);
		}

		return response;
	}

	public List<ComplaintDetailsDto> getOfficerNewComplaintDetails(Long officerId) {
		// Pull the raw Complaint entities first
		List<Complaint> complaints = complaintRepository.findByOfficerOfficerIdAndNoResolvedUpdate(officerId);
		// Convert to DTO exactly like the existing method
		List<ComplaintDetailsDto> response = new ArrayList<>();
		for (Complaint c : complaints) {
			ComplaintDetailsDto dto = mapComplaintToDto(c);
			response.add(dto);
		}
		return response;
	}

	public List<ComplaintDetailsDto> getOfficerPendingComplaintDetails(Long officerId) {
		List<Complaint> complaints = complaintRepository.findByOfficerOfficerIdAndLatestStatusInProgress(officerId);
		List<ComplaintDetailsDto> response = new ArrayList<>();
		for (Complaint c : complaints) {
			ComplaintDetailsDto dto = mapComplaintToDto(c);
			response.add(dto);
		}
		return response;
	}



	private ComplaintDetailsDto mapComplaintToDto(Complaint c) {
		ComplaintDetailsDto dto = new ComplaintDetailsDto();
		dto.setComplaintId(c.getComplaintId());
		dto.setCreatedAt(c.getCreatedAt());
		dto.setType(c.getType().toString());
		dto.setOriginalDescription(c.getOriginalDescription());
		dto.setSummarizedDescription(c.getSummarizedDescription());
		dto.setPredictedDepartment(c.getPredictedDepartment());
		dto.setImageUrl(c.getImageUrl());
		dto.setLocation(c.getLocation());
		// Guard against null user (just in case)
		if (c.getUser() != null) {
			dto.setUserId(c.getUser().getUserId());
		}
		// Officer id is already known from path variable, but we keep it for symmetry
		if (c.getOfficer() != null) {
			dto.setOfficerId(c.getOfficer().getOfficerId());
		}
		return dto;
	}

	public List<ComplaintDetailsDto> getOfficerResolvedComplaintDetails(Long officerId) {
		// Pull the raw Complaint entities using the repository query we just added
		List<Complaint> complaints = complaintRepository.findByOfficerOfficerIdAndResolved(officerId);
		// Convert each Complaint entity to the DTO exactly like the other methods
		List<ComplaintDetailsDto> response = new ArrayList<>();
		for (Complaint c : complaints) {
			ComplaintDetailsDto dto = mapComplaintToDto(c);   // reuse the private helper
			response.add(dto);
		}
		return response;
	}
}
