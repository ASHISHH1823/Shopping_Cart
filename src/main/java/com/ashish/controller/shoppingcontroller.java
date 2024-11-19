package com.ashish.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.ashish.entity.category;
import com.ashish.entity.product;
import com.ashish.entity.userdetails;
import com.ashish.service.CartService;
import com.ashish.service.categoryserviceimpl;
import com.ashish.service.productserviceimpl;
import com.ashish.service.userserviceimpl;
import com.ashish.util.CommonUtil;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class shoppingcontroller {
	@Autowired
	private categoryserviceimpl service;

	@Autowired
	private productserviceimpl pservice;

	@Autowired
	private userserviceimpl userservice;

	@Autowired
	private CartService cartService;

	@Autowired
	private CommonUtil CommonUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute
	public void getuserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			userdetails userdetails = userservice.getuserByemail(email);
			m.addAttribute("user", userdetails);
			Integer getcountCart = cartService.getcountCart(userdetails.getId());
			m.addAttribute("countCart", getcountCart);
		}
		List<category> allCategory = service.getallcategory();
		m.addAttribute("categ", allCategory);

	}

	@RequestMapping("/")
	public String homepage(Model m) {
		List<category> getallActiveCategory = service.getallActiveCategory().stream().sorted((c1,c2)->c2.getId().compareTo(c1.getId()))
				.limit(6).toList();
		List<product> getallActiveProduct = pservice.getallActiveProduct("").stream().sorted((p1,p2)->p2.getId().compareTo(p1.getId()))
				.limit(8).toList();
		m.addAttribute("categorys",getallActiveCategory);
		m.addAttribute("products",getallActiveProduct);
		return "index";
	}

	@RequestMapping("/signin")
	public String loginpage(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, Model m) {
		if (error != null) {
			m.addAttribute("error", "invalid password");
		}
		if (logout != null) {
			m.addAttribute("logout", "logout sucessfully");
		}
		return "login";
	}

	@RequestMapping("/register")
	public String registerpage() {
		return "register";
	}

	@RequestMapping("/products")
	public String productpage(Model m, @RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(name = "pageNo",defaultValue = "0")Integer pageNo,
			@RequestParam(name = "pageSize",defaultValue = "2")Integer pageSize,@RequestParam(defaultValue = "") String ch) {
		List<category> categories = service.getallActiveCategory();
		m.addAttribute("paramValue", category);
		m.addAttribute("categorie", categories);
		
		
		List<product> products = pservice.getallActiveProduct(category);
		m.addAttribute("products", products);
		Page<product> page =null;
		if(StringUtils.isEmpty(ch)) {
			page=pservice.getAllActiveProductPagenation(pageNo, pageSize, category);
		}else {
			page=pservice.getsearchActiveproductPagenation(pageNo,pageSize,category,ch);
		}
		
		
		List<product> productss=page.getContent();
		m.addAttribute("products",productss);
		m.addAttribute("productSize",productss.size());
		m.addAttribute("pageNo",page.getNumber());
		m.addAttribute("pageSize",pageSize);
		m.addAttribute("Totalelement",page.getTotalElements());
		m.addAttribute("Totalpages",page.getTotalPages());
		m.addAttribute("isFirst",page.isFirst());
		m.addAttribute("isLast",page.isLast());
		
		return "product";
	}

	@RequestMapping("/viewproduct/{id}")
	public String viewproduct(@PathVariable int id, Model m) {
		product product = pservice.getproduct(id);
		m.addAttribute("product", product);
		return "view_product";
	}

	@PostMapping("/saveusers")
	public String saveuser(@ModelAttribute userdetails user, HttpSession session,
			@RequestParam("file") MultipartFile file) throws IOException {
Boolean existEmail = userservice.existEmail(user.getEmail());
if(existEmail) {
	session.setAttribute("errorMsg", "Email alredy exist");
}else {
	

		String imagename = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		user.setProfileimage(imagename);
		userdetails saveUser = userservice.saveuser(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			if (!file.isEmpty()) {
				File savefile = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "profileimg" + File.separator
						+ file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			session.setAttribute("succMsg", "saved successfully");

		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
}

		return "redirect:/register";
	}

	@GetMapping("/forgot-password")
	public String ShowForgotPassword() {
		return "forget_password";
	}

	@PostMapping("/sendingemail")
	public String EmailChecking(@RequestParam String email, HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
		userdetails getuserByemail = userservice.getuserByemail(email);
		if (ObjectUtils.isEmpty(getuserByemail)) {
			session.setAttribute("errorMsg", "Invalid Email");
		} else {
			String resetToken = UUID.randomUUID().toString();
			userservice.UpdateUserResetToken(email, resetToken);
			String url = CommonUtil.generateUrl(request) + "/reset-password?token=" + resetToken;

			Boolean sendemail = CommonUtil.sendemail(url, email);
			if (sendemail) {
				session.setAttribute("succMsg", "plz check your email to reset password!");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
			}
		}
		return "redirect:/forgot-password";
	}

	@GetMapping("/reset-password")
	public String showResetPassword(@RequestParam String token, Model m) {
		userdetails userByToken = userservice.getUserByToken(token);
		if (userByToken == null) {
			m.addAttribute("msg", "your link is invalid or expired");
			return "message";
		}
		m.addAttribute("token", token);
		return "reset_password";
	}

	@PostMapping("/reseting_password")
	public String ResetPassword(@RequestParam String token, @RequestParam String password, Model m) {
		userdetails userByToken = userservice.getUserByToken(token);
		if (userByToken == null) {
			m.addAttribute("msg", "your link is invalid or expired");
			return "message";
		} else {
			userByToken.setPassword(passwordEncoder.encode(password));
			userByToken.setReset_token(null);
			userservice.updateUser(userByToken);
			m.addAttribute("msg", "password changed successfully!!");
			return "message";
		}
	}
	/*@GetMapping("/search")
	public String searchProduct(@RequestParam String ch,Model m) {
		List<product> searchproduct = pservice.searchproduct(ch);
		m.addAttribute("products",searchproduct);
		List<category> categories = service.getallActiveCategory();
		m.addAttribute("categorie", categories);
		return "product";
	}*/
	

}
