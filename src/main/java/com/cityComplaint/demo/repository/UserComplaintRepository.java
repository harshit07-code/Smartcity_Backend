package com.cityComplaint.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.UserComplaint;

import jakarta.persistence.Id;

@Repository
public interface UserComplaintRepository extends JpaRepository<UserComplaint, Long>{
 List<UserComplaint> findByUserUserId(Long userId);
}
