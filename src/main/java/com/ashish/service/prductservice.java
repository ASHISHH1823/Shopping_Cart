package com.ashish.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.entity.product;

public interface prductservice {
	public product saveproduct(product product);
	public List<product> getallproducts();
	public void deleteproduct(int id);
	public product getproduct(int id);
	public product updateproduct(product product,MultipartFile image);
	public List<product> getallActiveProduct(String category);
	public List<product> searchproduct(String ch);
	public Page<product> getAllActiveProductPagenation(Integer pageNO,Integer pageSize,String category);
	public Page<product> searchproductPagination(Integer pageNo,Integer pageSize,String ch);
	public Page<product> getallproductsPagenation(Integer pageNo,Integer pageSize);
	public Page<product> getsearchActiveproductPagenation(Integer pageNo, Integer pageSize, String category,
			String ch);

}
