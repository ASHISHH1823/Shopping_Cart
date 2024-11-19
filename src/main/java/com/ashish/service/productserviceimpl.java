package com.ashish.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.entity.product;
import com.ashish.repo.productrepo;

@Service
public class productserviceimpl implements prductservice {
	@Autowired
	private productrepo repo;

	@Override
	public product saveproduct(product product) {
		product save = repo.save(product);
		return save;
	}

	@Override
	public List<product> getallproducts() {
		List<product> productlist = repo.findAll();
		return productlist;
	}
	
	@Override
	public Page<product> getallproductsPagenation(Integer pageNo, Integer pageSize) {
		Pageable pageable= PageRequest.of(pageNo, pageSize);
		 return repo.findAll(pageable);
		
	}

	@Override
	public void deleteproduct(int id) {
		repo.deleteById(id);

	}

	@Override
	public product getproduct(int id) {
		product get = repo.findById(id).orElse(null);
		return get;
	}

	@Override
	public product updateproduct(product product,MultipartFile image)  {
		product edit=getproduct(product.getId());
		String imagename=image.isEmpty() ? edit.getImagename() : image.getOriginalFilename();
		edit.setTitle(product.getTitle());
		edit.setDescription(product.getDescription());
		edit.setCategory(product.getCategory());
		edit.setPrice(product.getPrice());
		edit.setStock(product.getStock());
		edit.setImagename(imagename);
		edit.setDiscount(product.getDiscount());
		edit.setIsactive(product.getIsactive());
	
		edit.setDiscountprice(product.getDiscountprice());
		double discount=product.getPrice() * (product.getDiscount()/100.0);
		double discountprice=product.getPrice()-discount;
		edit.setDiscountprice(discountprice);
		product upadateproduct=repo.save(edit);
		if(!ObjectUtils.isEmpty(upadateproduct)) {
			if(!image.isEmpty()) {
				try {
					File savefile=new ClassPathResource("static/images").getFile();
					Path path=Paths.get(savefile.getAbsolutePath() + File.separator + "productimg" + File.separator + image.getOriginalFilename());
					Files.copy(image.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			return product;
		}
		
		return null;
	}

	@Override
	public List<product> getallActiveProduct(String category) {
		List<product> products=null;
		if(ObjectUtils.isEmpty(category)) {
			products=repo.findByIsactiveTrue();
		}else {
			products=repo.findByCategory(category);
		}
		
		return products;
	}

	@Override
	public List<product> searchproduct(String ch) {
		return repo.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(ch, ch);
		
	}
	
	@Override
	public Page<product> searchproductPagination(Integer pageNo, Integer pageSize,String ch) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		return repo.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(ch, ch,pageable);
	
	}

	@Override
	public Page<product> getAllActiveProductPagenation(Integer pageNO, Integer pageSize, String category) {
      Pageable pageable= PageRequest.of(pageNO, pageSize);
      Page<product> pageproduct=null;
      if(ObjectUtils.isEmpty(category)) {
    	  pageproduct=repo.findByIsactiveTrue(pageable);
		}else {
			pageproduct=repo.findByCategory(pageable,category);
		}
		return pageproduct;
	}

	public Page<product> getsearchActiveproductPagenation(Integer pageNo, Integer pageSize, String category,
			String ch) {
	Pageable pageable=PageRequest.of(pageNo, pageSize);
	Page<product> pageproduct=null;
  pageproduct= repo.findByisactiveTrueAndTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(ch,
			ch, pageable);

		return pageproduct;
	}
}
