package com.ashish.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.entity.cart;
import com.ashish.entity.category;
import com.ashish.entity.orderRequest;
import com.ashish.entity.productOrder;
import com.ashish.entity.userdetails;
import com.ashish.service.CartServiceImpl;
import com.ashish.service.categoryservice;
import com.ashish.service.productOrderserviceImpl;
import com.ashish.service.productserviceimpl;
import com.ashish.service.userserviceimpl;
import com.ashish.util.CommonUtil;
import com.ashish.util.OrderStatus;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private  userserviceimpl userservice;
	
	@Autowired
	private categoryservice service;
	
	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private productserviceimpl pservice;
	
	@Autowired
	private productOrderserviceImpl productservice;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@ModelAttribute
	public void getuserDetails(Principal p,Model m) {
		if(p != null) {
			String email = p.getName();
			userdetails userdetails=userservice.getuserByemail(email);
			m.addAttribute("user",userdetails);
			Integer getcountCart = cartService.getcountCart(userdetails.getId());
			m.addAttribute("countCart",getcountCart);
		}
		List<category> allCategory=service.getallcategory();
		m.addAttribute("categ",allCategory);
	}
	
	//@GetMapping("/")
	public String hom() {
		return "user/home";
	}
	@GetMapping("/addcart")
	public String addCart(@RequestParam Integer pid,@RequestParam Integer uid,HttpSession session) {
		cart savecart = cartService.Savecart(pid, uid);
		if(ObjectUtils.isEmpty(savecart)) {
			session.setAttribute("errorMsg", "product add to cart failed!");
		}else {
			session.setAttribute("succMsg", "product added");
		}
		return "redirect:/viewproduct/"+pid;
	}
	@GetMapping("/cart")
	public String loadcartpage(Principal p,Model m) {
		userdetails user=getLoggedInUserdetails(p);
		List<cart> cartsByuser = cartService.getCartsByuser(user.getId());
		m.addAttribute("carts",cartsByuser);
		if(cartsByuser.size()>0) {
			Double totalorderprice = cartsByuser.get(cartsByuser.size()-1).getTotalorderprice();
			m.addAttribute("totalorder",totalorderprice);
		
		}
		
		
		return "user/cart";
	}
	@GetMapping("/CartQuantityUpdate")
	public String UpdateQuantity(@RequestParam String sy,@RequestParam Integer cid) {
		cartService.UpdateQuantity(sy, cid);
		return "redirect:/user/cart";
	}
	@GetMapping("/prdelete/{id}")
	public String deleteitem(@PathVariable int id,HttpSession session) {
		try {
			cartService.deletecartproduct(id);
			session.setAttribute("succMsg", "delete product successfully");
		} catch (Exception e) {
			session.setAttribute("errorMsg", "failed to delete product!, try again!");
		}
		return "redirect:/user/cart";
	}
   
	
	@GetMapping("/order")
	public String OrderAdress(Model m,Principal p) {
		m.addAttribute("orderRequest",new orderRequest());
		userdetails user=getLoggedInUserdetails(p);
		List<cart> cartsByuser = cartService.getCartsByuser(user.getId());
		m.addAttribute("carts",cartsByuser);
		if(cartsByuser.size()>0) {
			Double orderprice = cartsByuser.get(cartsByuser.size()-1).getTotalorderprice();
			Double totalorderprice=cartsByuser.get(cartsByuser.size()-1).getTotalorderprice() + 40;
			m.addAttribute("totalorder",orderprice);
			m.addAttribute("totalorderprice",totalorderprice);
		
		}
		return "user/OrderAdress";
	}
	
	@PostMapping("/saveadd")
	public String saveadress(@ModelAttribute orderRequest orderRequest,Principal p) throws UnsupportedEncodingException, MessagingException {
		userdetails loggedInUserdetails = getLoggedInUserdetails(p);
		productservice.saveproductOrder(loggedInUserdetails.getId(), orderRequest);
		return "redirect:/user/payments";
	}
	@GetMapping("/payments")
	public String chosepaymenttype( Model m) {
		m.addAttribute("orderRequest",new orderRequest());
		return "user/payment";
	}
	@PostMapping("/payments")
	public String chosepayment(@ModelAttribute orderRequest orderRequest,Principal p,@RequestParam("paymentType") String paymentType) {
		userdetails loggedInUserdetails = getLoggedInUserdetails(p);
		productservice.savepaymentType(loggedInUserdetails.getId(), paymentType);
		return "user/sucess";
	}
	@GetMapping("/user-orders")
	  public String myorders(Model m,Principal p) {
		userdetails loggedInUsers = getLoggedInUserdetails(p);
		List<productOrder> orders=productservice.getorderByuser(loggedInUsers.getId());
		m.addAttribute("orders",orders);
		 return "user/my_orders"; 
	  }
	@GetMapping("/update-status")
	public String updateuserStatus(@RequestParam Integer id,@RequestParam Integer st,HttpSession session) {
		OrderStatus[] values = OrderStatus.values();
		String status =null;
		for(OrderStatus orderst:values) {
			if(orderst.getId().equals(st)) {
				status = orderst.getName();
			}
		}
		productOrder updateOrderStatus = productservice.updateOrderStatus(id, status);
		
		
			try {
				commonUtil.sendMailforproductorder(updateOrderStatus, status);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(!ObjectUtils.isEmpty(updateOrderStatus)) {
			session.setAttribute("succMsg", "Status updated");
		}else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "redirect:/user/user-orders";
	}
	
	private userdetails getLoggedInUserdetails(Principal p) {
		String name = p.getName();
		userdetails getuserDetailsemail = userservice.getuserByemail(name);
		return getuserDetailsemail;
		
	}
	@GetMapping("/profiles")
	public String Profilepage() {
		return "user/profile";
	}
	@PostMapping("/update-profile")
	public String updateprofile(@ModelAttribute userdetails user,@RequestParam MultipartFile img,HttpSession session) {
		userdetails updateuserprofile = userservice.updateprofileuser(user, img);
		if(!ObjectUtils.isEmpty(updateuserprofile)) {
			session.setAttribute("succMsg","User profile updated sucessfully");
		}else {
			session.setAttribute("errorMsg", "something wrong");
		}
		return"redirect:/user/profiles";
	}
	@PostMapping("/change-password")
	public String Changepassword(@RequestParam String currentpassword,@RequestParam String newpassword,Principal p,HttpSession session) {
		userdetails loggedInUserdetails = getLoggedInUserdetails(p);
	     boolean matches = passwordEncoder.matches(currentpassword, loggedInUserdetails.getPassword());
	     if(matches) {
	    	 String encodepassword = passwordEncoder.encode(newpassword);
	    	 loggedInUserdetails.setPassword(encodepassword);
	    	 userdetails updateUser = userservice.updateUser(loggedInUserdetails);
	    	 if(!ObjectUtils.isEmpty(updateUser)) {
	    		 session.setAttribute("succMsg", "password changed sucessfully");
	    	 }else {
	    		session.setAttribute("errorMsg", "password not changed!! something wrong on server"); 
	    	 }
	     }else{
	     session.setAttribute("errorMsg", "Current password incorect");
	     }
		return "redirect:/user/profiles";
	}
	

}
