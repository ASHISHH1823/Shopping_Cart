package com.ashish.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ashish.entity.category;

public interface categoryservice {
	public category savecategory(category category);
	public List<category> getallcategory();
	public Boolean existCategory(String name);
	public void deletecategory(int id);
	public category updatecateory(category category);
	public category getcategory(int id);
	public List<category> getallActiveCategory();
	public Page<category> getAllCategoryPagination(Integer pageNo,Integer pageSize);
	

}
