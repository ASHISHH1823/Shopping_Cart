package com.ashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.productOrder;

public interface productOrderRepository extends JpaRepository<productOrder, Integer> {

	List<productOrder> findByuserId(Integer userid);
	productOrder findByOrderid(String orderid);
	

}
