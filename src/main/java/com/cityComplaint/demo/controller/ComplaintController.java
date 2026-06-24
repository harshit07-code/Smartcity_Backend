package com.cityComplaint.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.cityComplaint.demo.Entity.Department;
import com.cityComplaint.demo.Entity.Officer;
import com.cityComplaint.demo.Entity.User;
import com.cityComplaint.demo.repository.DepartmentRepository;
import com.cityComplaint.demo.service.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.dto.GeminiResponse;
import com.cityComplaint.demo.dto.MoreComplaintDetailsResponse;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private UserService userService;

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private GeminiService geminiService;

	@Autowired
	ComplaintUpdatesService complaintUpdatesService;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private OfficerService officerService;

	@Autowired
	private DepartmentService departmentService;

	// Method for creation of a Complaint , User register a complaint the request
	// will come here.
	
	
	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> registerComplaint(

			@RequestParam("originalDescription") String originalDescription,

			@RequestParam("location") String location,

			@RequestParam("type") Complaint.Type type,
			@RequestParam("userId") Long userId,

			@RequestParam("image") MultipartFile image

	) throws Exception {

		// 1️⃣ Load the User entity (throws if not found)

		User user = userService.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid userId: " + userId));

		Complaint complaint = new Complaint();

		complaint.setOriginalDescription(originalDescription);

		complaint.setLocation(location);

		complaint.setType(type);


//		String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
////
////		Path path = Paths.get("src/main/resources/static/images/" + fileName);
////		Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////
////		complaint.setImageUrl("/images/" + fileName);
//		String uploadDir = "uploads/images/";
//
//		File dir = new File(uploadDir);
//
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//
//		Path path = Paths.get(uploadDir + fileName);
//
//		Files.copy(
//				image.getInputStream(),
//				path,
//				StandardCopyOption.REPLACE_EXISTING
//		);
//		System.out.println(path.toAbsolutePath());
//
//		complaint.setImageUrl("/images/" + fileName);

		String imageUrl =
				cloudinaryService.uploadImage(image);

		complaint.setImageUrl(imageUrl);
		complaint.setUser(user);

		GeminiResponse aiResponse = geminiService.askGemini(originalDescription);

		complaint.setSummarizedDescription(aiResponse.getSummary());

		complaint.setPredictedDepartment(aiResponse.getDepartment());

		String deptCode = aiResponse.getDepartment();

		if (deptCode == null || deptCode.isBlank()) {
			deptCode = "GENERAL";
		}

		deptCode = deptCode.toUpperCase().trim();

		Department department =
				departmentRepository.findByDeptCodeIgnoreCase(deptCode);

		if (department == null) {

			department =
					departmentRepository.findByDeptCodeIgnoreCase("GENERAL");

			if (department == null) {

				Department generalDepartment = new Department();

				generalDepartment.setDeptName("General Department");
				generalDepartment.setDeptCode("GENERAL");

				department =
						departmentRepository.save(generalDepartment);
			}
		}
		complaint.setDepartment(department);

		Officer officer =
				officerService.getLeastBusyOfficer(
						department.getDeptId());

		complaint.setOfficer(officer);


		return ResponseEntity.ok(complaintService.complaintRegistration(complaint));

	}
	
	
	//when user clicks See more details then the request will come here 

	@GetMapping("/details/{complaintId}")
	public ResponseEntity<ResponseEntity<MoreComplaintDetailsResponse>> getMoreDetails(@PathVariable Long complaintId) {

		return ResponseEntity.ok(complaintUpdatesService.getMoreDetails(complaintId));
	}
	
	

}
