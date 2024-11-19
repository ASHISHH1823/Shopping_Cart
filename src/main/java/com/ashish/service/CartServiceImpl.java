package com.ashish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ashish.entity.cart;
import com.ashish.entity.product;
import com.ashish.entity.userdetails;
import com.ashish.repo.CartRepo;
import com.ashish.repo.productrepo;
import com.ashish.repo.userrepo;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private productrepo productrepo;
	
	@Autowired
	private userrepo userrepo;
	
	

	@Override
	public cart Savecart(Integer productid, Integer userid) {
		userdetails userdetails = userrepo.findById(userid).get();
	     product product = productrepo.findById(productid).get();
	   cart  cartStatus= cartRepo.findByProduct_IdAndUser_Id(productid, userid);
	     cart cart=null;
	     if(ObjectUtils.isEmpty(cartStatus)) {
	    	 cart=new cart();
	    	 cart.setProduct(product);
	    	 cart.setQuantity(1);
	    	 cart.setUser(userdetails);
	    	 cart.setTotalprice(1*product.getDiscountprice()); 
	     }else {
	    	 cart=cartStatus;
	    	 cart.setQuantity(cart.getQuantity()+1);
	    	 cart.setTotalprice(cart.getQuantity()* cart.getProduct().getDiscountprice());
	    	 
	     }
	     cart save= cartRepo.save(cart);
		return save;
	}

	@Override
	public List<cart> getCartsByuser(Integer userid) {
	       List<cart> carts = cartRepo.findByuserId(userid);
	       Double totalorderprice=0.0;
	       List<cart> upadatecarts=new ArrayList<>();
	       for(cart c:carts) {
	    	   Double totalprice=( c.getProduct().getDiscountprice()* c.getQuantity());
	       c.setTotalprice(totalprice);
	       totalorderprice=totalorderprice + totalprice;
	       c.setTotalorderprice(totalorderprice);
	       upadatecarts.add(c);
	       }
		return upadatecarts;
	}

	@Override
	public Integer getcountCart(Integer userid) {
		Integer countByuser_id = cartRepo.countByuser_id(userid);
		return countByuser_id ;
	}

	@Override
	public void UpdateQuantity(String sy, Integer cid) {
		cart cart = cartRepo.findById(cid).get();
		int updatequantity;
		if(sy.equalsIgnoreCase("de")) {
			updatequantity=cart.getQuantity()-1;
			if(updatequantity <= 0) {
				updatequantity=1;
			}else {
				cart.setQuantity(updatequantity);
				cartRepo.save(cart);
			}
		}
		else {
			updatequantity=cart.getQuantity() +1;
			cart.setQuantity(updatequantity);
			cartRepo.save(cart);
		}
	}

	@Override
	public void deletecartproduct(int id) {
		cartRepo.deleteById(id);
		
	}

}
