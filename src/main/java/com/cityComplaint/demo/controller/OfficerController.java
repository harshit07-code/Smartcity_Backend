package com.cityComplaint.demo.controller;

import java.util.List;

import com.cityComplaint.demo.Entity.Department;
import com.cityComplaint.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.Entity.User;
import com.cityComplaint.demo.dto.ComplaintDetailsDto;
import com.cityComplaint.demo.dto.OfficerDTO;
import com.cityComplaint.demo.dto.OfficerLoginResponse;
import com.cityComplaint.demo.repository.OfficerRepository;
import com.cityComplaint.demo.service.ComplaintDetailsDtoService;
import com.cityComplaint.demo.service.OfficerService;

@RestController
@RequestMapping("/officer")
public class OfficerController {

	@Autowired
	private OfficerService officerService;

	@Autowired
	private OfficerRepository officerRepository;

	@Autowired
	private ComplaintDetailsDtoService complaintDetailsDtoService;


	@Autowired
	private DepartmentRepository departmentRepository;
	// Officer Creation method

//	@PostMapping("/create")
//	public ResponseEntity<Officer> createOfficer(@RequestBody Officer officer) {
//		Officer savedOfficer = officerService.create(officer);
//		return ResponseEntity.ok(savedOfficer);
//	}

	@PostMapping("/create")
	public ResponseEntity<?> createOfficer(
			@RequestBody OfficerDTO request) {

		Department department =
				departmentRepository.findById(request.getDeptId())
						.orElseThrow(() ->
								new RuntimeException("Department not found"));

		Officer officer = new Officer();

		officer.setName(request.getName());
		officer.setEmail(request.getEmail());
		officer.setPassword(request.getPassword());
		officer.setPhoneNo(request.getPhoneNo());

		officer.setDepartment(department);
		System.out.println("Name = " + request.getName());
		return ResponseEntity.ok(
				officerRepository.save(officer)
		);


	}
	
	

	// Officer Login Details Check Method

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Officer officer) {

		Officer existingOfficer = officerRepository.findByEmail(officer.getEmail());

		if (existingOfficer == null) {

			return ResponseEntity.badRequest().body("Officer Not Found");
		}

		if (!existingOfficer.getPassword().equals(officer.getPassword())) {

			return ResponseEntity.badRequest().body("Wrong Password");
		}

		OfficerLoginResponse response = new OfficerLoginResponse();

		response.setOfficerId(existingOfficer.getOfficerId());

		response.setName(existingOfficer.getName());

		response.setEmail(existingOfficer.getEmail());

		return ResponseEntity.ok(response);
	}


	//Request come from Officer dashBoard when user clicks show all complaints..........
   //Return AllComplaints to Show all complaint Button.......
	@GetMapping("/complaints/{officerId}")
	public ResponseEntity<List<ComplaintDetailsDto>> getOfficerComplaints(@PathVariable Long officerId) {
		List<ComplaintDetailsDto> complaintDetails;

		complaintDetails = complaintDetailsDtoService.getOfficerComplaintDetails(officerId);

		return ResponseEntity.ok(complaintDetails);
	}


//It will return the officer detail from the user complaint details page when user click the button officer detail .
	@GetMapping("/officerDetails/{officerId}")
	public ResponseEntity<OfficerDTO> getOfficerDetails(@PathVariable Long officerId) {
		// Delegate to the service layer (the method already exists there)
		return officerService.getOfficerDetails(officerId);
	}



	//It will return details to new complaints button in officer Dashboard..............

	@GetMapping("/NewComplaints/{officerId}")
	public ResponseEntity<List<ComplaintDetailsDto>> getOfficerNewComplaints(@PathVariable Long officerId) {
		List<ComplaintDetailsDto> complaintDetails =
				complaintDetailsDtoService.getOfficerNewComplaintDetails(officerId);
		return ResponseEntity.ok(complaintDetails);
	}


	//It will return details to pending complaints button in Officer Dashboard........
	@GetMapping("/PendingComplaints/{officerId}")
	public ResponseEntity<List<ComplaintDetailsDto>> getOfficerPendingComplaints(@PathVariable Long officerId) {
		List<ComplaintDetailsDto> complaintDetails =
				complaintDetailsDtoService.getOfficerPendingComplaintDetails(officerId);
		return ResponseEntity.ok(complaintDetails);
	}


	//Return Resolved Complaints to button Resolved Complaints.. in Officer Dashboard....

	@GetMapping("/ResolvedComplaints/{officerId}")
	public ResponseEntity<List<ComplaintDetailsDto>> getOfficerResolvedComplaints(@PathVariable Long officerId) {
		List<ComplaintDetailsDto> complaintDetails;

		complaintDetails = complaintDetailsDtoService.getOfficerResolvedComplaintDetails(officerId);

		return ResponseEntity.ok(complaintDetails);
	}

}
