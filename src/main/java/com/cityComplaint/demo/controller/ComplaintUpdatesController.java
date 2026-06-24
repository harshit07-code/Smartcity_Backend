package com.cityComplaint.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityComplaint.demo.Entity.ComplaintUpdates;
import com.cityComplaint.demo.service.ComplaintUpdatesService;

@RestController
@RequestMapping("/updates")
public class ComplaintUpdatesController {

	@Autowired
	private ComplaintUpdatesService complaintUpdatesService;

	@PutMapping("/{complaintId}")
	public ResponseEntity<ComplaintUpdates> updateComplaint(@PathVariable Long complaintId, @RequestBody ComplaintUpdates complaintUpdates) {

		ComplaintUpdates updateComplaint = complaintUpdatesService.update(complaintId, complaintUpdates);
		return ResponseEntity.ok(updateComplaint);
	}

	@PutMapping("/{complaintId}/{officerId}")
	public String complaintUpdates(@RequestBody ComplaintUpdates complaintUpdates, @PathVariable Long complaintId, @PathVariable Long officerId) {
		String message = complaintUpdatesService.updateDetails(complaintId, officerId, complaintUpdates);
		return message;
	}
}
