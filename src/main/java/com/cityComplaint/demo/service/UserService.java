package com.cityComplaint.demo.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cityComplaint.demo.Entity.User;
import com.cityComplaint.demo.repository.UserRepository;

@Service
public class UserService {
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser( User user) {
		user.setCreatedAt(LocalDateTime.now());
		
		return userRepository.save(user);
	}

	public Optional<User> findById(Long userId){
		return userRepository.findById(userId);
	}
}
