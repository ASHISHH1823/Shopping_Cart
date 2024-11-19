package com.ashish.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ashish.entity.orderRequest;
import com.ashish.entity.productOrder;

import jakarta.mail.MessagingException;

public interface productOrderservice {
	public void saveproductOrder(Integer userid,orderRequest orderRequest) throws UnsupportedEncodingException, MessagingException;
	public void savepaymentType(Integer userid ,String paymentType);
	public List<productOrder> getorderByuser(Integer userid);
	public productOrder updateOrderStatus(Integer id,String status);
	public List<productOrder> getallOrders();
	public productOrder getOrdersByOrderid(String orderid);
	public Page<productOrder> getallOrdersPagenation(Integer pageNo,Integer pageSize);

}
