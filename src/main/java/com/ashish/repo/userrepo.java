package com.ashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.userdetails;

public interface userrepo extends JpaRepository<userdetails, Integer> {
	
	public userdetails findByemail(String email);

	public List<userdetails> findByRole(String role);

	public userdetails findByresetToken(String token);
	
	public Boolean existsByEmail(String email);

}
