package com.cityComplaint.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cityComplaint.demo.Entity.Complaint;
import com.cityComplaint.demo.Entity.User;
import com.cityComplaint.demo.Entity.UserComplaint;
import com.cityComplaint.demo.dto.ComplaintDetailsDto;
import com.cityComplaint.demo.dto.MoreComplaintDetailsResponse;
import com.cityComplaint.demo.repository.UserRepository;
import com.cityComplaint.demo.service.ComplaintService;
import com.cityComplaint.demo.service.ComplaintUpdatesService;
import com.cityComplaint.demo.service.OfficerService;
import com.cityComplaint.demo.service.UserComplaintService;
import com.cityComplaint.demo.service.UserService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {
		"http://localhost:3000",
		"https://smartcity-frontend-six.vercel.app"
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	// Autowiring

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private UserComplaintService userComplaintService;

	// Autowiring End

	// Methods

	// This will open my landing page

	@GetMapping("/home")
	public String home() {
		return "home Page Visible ";
	}

	// This method will called when user is being registered.
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {

		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}

	// This method is called when user want to login to check his credentails.

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginData) {
		User user = userRepository.findByEmail(loginData.getEmail());

		if (user != null && user.getPassword().equals(loginData.getPassword())) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().body("Invalid Email or Password");
		}
	}

	// This method is called when user want to see his complaints by clicking see my
	// complaints button
	
//	@GetMapping("/complaints/user/{userId}")
//	public List<Complaint> getComplaints(@PathVariable Long userId) {
//
//		List<Complaint> list = complaintService.getComplaintDetails(userId);
//
//		return list;
//
//	}



	@GetMapping("/complaints/user/{userId}")
	public ResponseEntity<List<ComplaintDetailsDto>> getUserComplaints(@PathVariable Long userId) {
		List<ComplaintDetailsDto> dtos = complaintService.getComplaintDetails(userId);
		return ResponseEntity.ok(dtos);
	}

}
