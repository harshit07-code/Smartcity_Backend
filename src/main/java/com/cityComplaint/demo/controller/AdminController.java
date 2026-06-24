package com.cityComplaint.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.Admin;
import com.cityComplaint.demo.repository.AdminRepository;
import com.cityComplaint.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Admin loginData){
		Admin admin = adminRepository.findByEmail(loginData.getEmail());
		
		if(admin!=null  && admin.getPassword().equals(loginData.getPassword())) {
			return ResponseEntity.ok(admin);
		}else {
			return ResponseEntity.badRequest().body("Inavalid credentials");
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createAdmin(@RequestBody Admin admin){
		
		if(admin.getEmail().equals(adminRepository.findByEmail(admin.getEmail()))) {
			return ResponseEntity.badRequest().body("Admin with same Email already exists");
		}else {
			return ResponseEntity.ok(adminService.save(admin));
		}
		
	}

}
