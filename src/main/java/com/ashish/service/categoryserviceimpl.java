package com.ashish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ashish.entity.category;
import com.ashish.repo.categoryrepo;

@Service
public class categoryserviceimpl implements categoryservice {
	@Autowired
	private categoryrepo repo;

	@Override
	public category savecategory(category category) {
		category save=repo.save(category);
		return save;
	}
	@Override
	public List<category> getallcategory() {
	     List<category>categorylist=(List<category>)repo.findAll();
		return categorylist;
	}

	@Override
	public Page<category> getAllCategoryPagination(Integer pageNo, Integer pageSize) {
	Pageable pageable=PageRequest.of(pageNo, pageSize);
	return repo.findAll(pageable);
	}
	@Override
	public Boolean existCategory(String name) {
		// TODO Auto-generated method stub
		return repo.existsByName(name);
	}

	@Override
	public void deletecategory(int id) {
		repo.deleteById(id);
	
		
	}

	@Override
	public category updatecateory(category category) {
		category save=repo.save(category);
		return save;
	}
	@Override
	public category getcategory(int id) {
		category get=repo.findById(id).orElse(null);
		return get;
	}
	
	@Override
	public List<category> getallActiveCategory() {
		// TODO Auto-generated method stub
		List<category> categories=repo.findByActiveTrue();
		return categories;
	}

	

}
