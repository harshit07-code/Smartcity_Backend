package com.cityComplaint.demo.service;

import com.cityComplaint.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.dto.OfficerDTO;
import com.cityComplaint.demo.repository.OfficerRepository;

import java.util.List;

@Service
public class OfficerService {
	
	@Autowired
	private OfficerRepository officerRepository;

	@Autowired
	private ComplaintRepository complaintRepository;

public Officer create(Officer officer) {
	return officerRepository.save(officer);
}

//public ResponseEntity<OfficerDTO> getOfficerDetails(Long officerId) {
//	OfficerDTO officerdto = new OfficerDTO();
//	 Officer officer  = officerRepository.findByOfficerId(officerId);
//	officerdto.setOfficerName(officer.getName());
//	officerdto.setOfficerId(officerId);
//	officerdto.setEmail(officer.getEmail());
//	officerdto.setPhoneNo(officer.getPhoneNo());
//	return ResponseEntity.ok(officerdto) ;
//}

	public ResponseEntity<OfficerDTO> getOfficerDetails(Long officerId) {

		Officer officer =
				officerRepository.findByOfficerId(officerId);

		OfficerDTO officerdto = new OfficerDTO();

//		officerdto.setOfficerId(officer.getOfficerId());
		officerdto.setName(officer.getName());

		officerdto.setEmail(officer.getEmail());
		officerdto.setPhoneNo(officer.getPhoneNo());

		officerdto.setDeptId(
				officer.getDepartment().getDeptId()
		);

		return ResponseEntity.ok(officerdto);
	}



	public Officer getLeastBusyOfficer(Long deptId) {

		List<Officer> officers =
				officerRepository.findByDepartmentDeptId(deptId);

		Officer selectedOfficer = null;

		long minCount = Long.MAX_VALUE;

		for(Officer officer : officers) {

			long complaintCount =
					complaintRepository.countByOfficerOfficerId(
							officer.getOfficerId()
					);

			if(complaintCount < minCount) {

				minCount = complaintCount;

				selectedOfficer = officer;
			}
		}

		return selectedOfficer;
	}
}
