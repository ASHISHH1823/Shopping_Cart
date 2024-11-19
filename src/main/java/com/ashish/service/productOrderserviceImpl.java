package com.ashish.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ashish.entity.OrderAdress;
import com.ashish.entity.cart;
import com.ashish.entity.orderRequest;
import com.ashish.entity.productOrder;
import com.ashish.repo.CartRepo;
import com.ashish.repo.orderadressrepo;
import com.ashish.repo.productOrderRepository;
import com.ashish.util.CommonUtil;
import com.ashish.util.OrderStatus;

import jakarta.mail.MessagingException;

@Service
public class productOrderserviceImpl implements productOrderservice {
	
	@Autowired
	private productOrderRepository productOrderRepository;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private orderadressrepo orderadressrepo;
	@Autowired
	private productOrderRepository proOrderRepository;
	@Autowired
    private CommonUtil commonUtil;
	

	@Override
	public void saveproductOrder(Integer userid, orderRequest orderRequest) throws UnsupportedEncodingException, MessagingException {

			OrderAdress adress=new OrderAdress();
			adress.setFirstname(orderRequest.getFirstname());
		    adress.setLastname(orderRequest.getLastname());
			adress.setEmail(orderRequest.getEmail());
			adress.setMobileno(orderRequest.getMobileno());
			adress.setCity(orderRequest.getCity());
			adress.setPincode(orderRequest.getPincode());
			adress.setState(orderRequest.getState());
			adress.setAddress(orderRequest.getAddress());
			
			adress=orderadressrepo.save(adress);
			List<cart> carts = cartRepo.findByuserId(userid);
			for(cart c:carts) {
				productOrder order=new productOrder();
				order.setOrderid(UUID.randomUUID().toString());
				order.setOrderDate(LocalDate.now());
				
				order.setProduct(c.getProduct());
				order.setPrice(c.getProduct().getDiscountprice());
				
				order.setQuantity(c.getQuantity());
				order.setUser(c.getUser());
				order.setStatus(OrderStatus.IN_PROGRESS.getName());
				//order.setPaymentType(orderRequest.getPaymentType());
				order.setOrderAdress(adress);
				productOrder saveorder = productOrderRepository.save(order);	
				commonUtil.sendMailforproductorder(saveorder, "success");
		}
}
	@Override
	public void savepaymentType(Integer userid, String paymentType) {
	List<productOrder> porder=productOrderRepository.findByuserId(userid);
	for(productOrder p:porder) {
		p.setPaymentType(paymentType);
		productOrderRepository.save(p);
	}
		}
	@Override
	public List<productOrder> getorderByuser(Integer userid) {
		List<productOrder> orders = productOrderRepository.findByuserId(userid);
		return orders;
	}
	@Override
	public productOrder updateOrderStatus(Integer id, String status) {
		Optional<productOrder> findbyId = productOrderRepository.findById(id);
		if(findbyId.isPresent()) {
			productOrder productOrder = findbyId.get();
			productOrder.setStatus(status);
			productOrder updateorder = productOrderRepository.save(productOrder);
			return updateorder;
		}
		return null;
	}
	@Override
	public List<productOrder> getallOrders() {
		
		return productOrderRepository.findAll();
	}
	@Override
	public Page<productOrder> getallOrdersPagenation(Integer pageNo, Integer pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		return productOrderRepository.findAll(pageable);
	}
	@Override
	public productOrder getOrdersByOrderid(String orderid) {
		
		return productOrderRepository.findByOrderid(orderid);
	}
	
	}
