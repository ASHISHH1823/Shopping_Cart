package com.ashish.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.product;

public interface productrepo extends JpaRepository<product, Integer> {


	List<product> findByIsactiveTrue();

	List<product> findByCategory(String category);
	List<product> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch,String ch2);

	Page<product> findByIsactiveTrue(Pageable pageable);

	Page<product> findByCategory(Pageable pageable, String category);

    Page<product> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2,
			Pageable pageable);

	Page<product> findByisactiveTrueAndTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2,
			Pageable pageable);



	//List<category> findByCategory(String category);

}
