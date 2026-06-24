package com.cityComplaint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
