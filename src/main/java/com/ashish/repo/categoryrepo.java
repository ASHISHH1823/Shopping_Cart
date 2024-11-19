package com.ashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.category;

public interface categoryrepo extends JpaRepository<category,Integer> {
	
	public Boolean existsByName(String name);

	

	public List<category> findByActiveTrue();

}
