package com.ashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.cart;

public interface CartRepo extends JpaRepository<cart,Integer>{
	public cart findByProduct_IdAndUser_Id(Integer productid,Integer userid);

	public Integer countByuser_id(Integer userid);
	public List<cart> findByuserId(Integer userid);

}
