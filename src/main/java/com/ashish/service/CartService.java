package com.ashish.service;

import java.util.List;

import com.ashish.entity.cart;

public interface CartService {
	public cart Savecart(Integer productid,Integer userid);
	public List<cart> getCartsByuser(Integer userid);
	public Integer getcountCart(Integer userid);
	public void UpdateQuantity(String sy,Integer cid);
	public void deletecartproduct(int id);

}
