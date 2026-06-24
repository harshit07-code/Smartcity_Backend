package com.cityComplaint.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Admin;
import com.cityComplaint.demo.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin save(Admin admin) {
		 return adminRepository.save(admin);
		
		
	}

}
