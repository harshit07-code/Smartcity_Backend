package com.cityComplaint.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.Feedback;
import com.cityComplaint.demo.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback){
		Feedback savedFeedback  = feedbackService.create(feedback);
		return ResponseEntity.ok(savedFeedback);
	}
}
