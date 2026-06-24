package com.cityComplaint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityComplaint.demo.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long userId);
User findByEmail(String email);


	}
	

