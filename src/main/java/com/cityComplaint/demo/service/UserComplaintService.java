package com.cityComplaint.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.UserComplaint;
import com.cityComplaint.demo.repository.UserComplaintRepository;

@Service
public class UserComplaintService {
	
	@Autowired 
	private UserComplaintRepository userComplaintRepository;
	
	public List<UserComplaint> getComplaintDetails(Long userId) {
		return userComplaintRepository.findByUserUserId(userId);
		
	}
	
//	public Long getDeptId(Long complaintId) {
//		userComplaintRepository.getDeptId(ComplaintId);
//	}

}
