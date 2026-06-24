package com.cityComplaint.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityComplaint.demo.Entity.Feedback;
import com.cityComplaint.demo.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired 
	private FeedbackRepository feedbackRepository;
	
	public Feedback create(Feedback feedback) {
		feedback.setCreatedAt(LocalDateTime.now());
		return feedbackRepository.save(feedback);
	}

}
