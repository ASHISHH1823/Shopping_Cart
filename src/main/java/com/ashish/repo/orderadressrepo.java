package com.ashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.OrderAdress;

public interface orderadressrepo extends JpaRepository<OrderAdress, Integer>{

	

}
