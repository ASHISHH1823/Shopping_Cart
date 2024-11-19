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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.ashish.entity.productOrder;
import com.ashish.entity.userdetails;
import com.ashish.service.categoryserviceimpl;
import com.ashish.service.productOrderserviceImpl;
import com.ashish.service.productserviceimpl;
import com.ashish.service.userserviceimpl;
import com.ashish.util.CommonUtil;
import com.ashish.util.OrderStatus;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class admincontroller {

	@Autowired
	private categoryserviceimpl service;

	@Autowired
	private productserviceimpl pservice;

	@Autowired
	private userserviceimpl userservice;
	@Autowired
	private productOrderserviceImpl productOrderservice;

	@Autowired
	private CommonUtil commonUtil;

	@ModelAttribute
	public void getuserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			userdetails userdetails = userservice.getuserByemail(email);
			m.addAttribute("user", userdetails);
		}
		List<category> allCategory = service.getallcategory();
		m.addAttribute("categ", allCategory);
	}

	@GetMapping("/")
	public String indexpage() {
		return "admin/index";
	}

	@GetMapping("/addproducts")
	public String addproduct(Model m) {
		List<category> cat = service.getallcategory();
		m.addAttribute("categoriess", cat);
		return "admin/add_product";
	}

	@GetMapping("/categories")
	public String categorypage(Model model,
			@RequestParam(name = "pageNo" ,defaultValue = "0")Integer pageNo,
			@RequestParam(name = "pageSize" ,defaultValue = "2")Integer pageSize) {
		//model.addAttribute("categorys", service.getallcategory());
		Page<category> page = service.getAllCategoryPagination(pageNo, pageSize);
		List<category> categorys=page.getContent();
		model.addAttribute("categorys",categorys);
		model.addAttribute("productSize",categorys.size());
		model.addAttribute("pageNo",page.getNumber());
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("Totalelement",page.getTotalElements());
		model.addAttribute("Totalpages",page.getTotalPages());
		model.addAttribute("isFirst",page.isFirst());
		model.addAttribute("isLast",page.isLast());
		
		return "admin/category";
	}

	@PostMapping("/savecategories")
	public String savecategory(@ModelAttribute category category, @RequestParam("file") MultipartFile file,
			HttpSession session) throws IOException {

		String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
		category.setImagename(imageName);

		Boolean existCategory = service.existCategory(category.getName());
		if (existCategory) {
			session.setAttribute("errorMsg", "category name alredy exist");
		} else {
			category savecategory = service.savecategory(category);
			if (ObjectUtils.isEmpty(savecategory)) {
				session.setAttribute("errorMsg", "Not saved! internal server error");
			} else {
				File saveFile = new ClassPathResource("static/images").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "categorysimg" + File.separator
						+ file.getOriginalFilename());

				// System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				session.setAttribute("succMsg", "saved Successfully");
			}

		}
		return "redirect:/admin/categories";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, HttpSession session) {
		try {
			service.deletecategory(id);
			session.setAttribute("succMsg", "category delete sucessfully");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("errorMsg", "failed to delete category, try again!");
		}
		return "redirect:/admin/categories";

	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable int id, ModelMap model) {
		model.addAttribute("command", service.getcategory(id));
		return "admin/editcategory";

	}

	@RequestMapping("/updatecategorys")
	public String updatecategory(@ModelAttribute category category, HttpSession session,
			@RequestParam("file") MultipartFile file) throws IOException {
	/*	category oldcategory = service.getcategory(category.getId());

		// Check if the file is not empty
		if (!file.isEmpty()) {
			String imageName = file.getOriginalFilename();
			oldcategory.setImagename(imageName);
		} else {
			// If file is empty, keep the existing imageName
			oldcategory.setImagename(oldcategory.getImagename());
		}
		if (!ObjectUtils.isEmpty(category)) {
			oldcategory.setName(category.getName());
			oldcategory.setActive(category.getisActive());
			oldcategory.setImagename(category.getImagename());

		}
		category updCategory = service.savecategory(oldcategory);

		if (!ObjectUtils.isEmpty(updCategory )) {
			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/images").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "categorysimg" + File.separator
						+ file.getOriginalFilename());

				// System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			session.setAttribute("succMsg", "category updated succesfully");
		} else {
			session.setAttribute("errorMsg", "Update failed,internal server error");
		}*/
		category oldCategory = service.getcategory(category.getId());
		String imageName = file.isEmpty() ? oldCategory.getImagename() : file.getOriginalFilename();

		if (!ObjectUtils.isEmpty(category)) {

			oldCategory.setName(category.getName());
			oldCategory.setActive(category.getisActive());
			oldCategory.setImagename(imageName);
		}

		category updateCategory = service.savecategory(oldCategory);

		if (!ObjectUtils.isEmpty(updateCategory)) {

			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/images").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "categorysimg" + File.separator
						+ file.getOriginalFilename());

				// System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			session.setAttribute("succMsg", "Category update success");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}

		return "redirect:/admin/categories";

	}

	@PostMapping("/saveproduct")
	public String saveproduct(@ModelAttribute product product, @RequestParam("file") MultipartFile image,
			HttpSession session) throws IOException {

		String imageName = image != null ? image.getOriginalFilename() : "default.jpg";
		product.setImagename(imageName);

		product.setDiscount(0);
		product.setDiscountprice(product.getPrice());
		product saveProduct = pservice.saveproduct(product);

		if (!ObjectUtils.isEmpty(saveProduct)) {

			File saveFile = new ClassPathResource("static/images").getFile();

			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "productimg" + File.separator
					+ image.getOriginalFilename());

			// System.out.println(path);
			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			session.setAttribute("succMsg", "Product Saved Success");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}

		return "redirect:/admin/addproducts";

	}

	@GetMapping("/products")
	public String viewproduct(Model model, @RequestParam(defaultValue = "") String ch,
			@RequestParam(name = "pageNo",defaultValue = "0")Integer pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize) {
	/*	List<product> products = null;
		if (ch != null && ch.length() > 0) {
			products = pservice.searchproduct(ch);
		} else {
			products = pservice.getallproducts();
		}
		model.addAttribute("products", products);
		*/
		
		Page<product> page = null;
		if (ch != null && ch.length() > 0) {
			page = pservice.searchproductPagination(pageNo, pageSize, ch);
		} else {
			page = pservice.getallproductsPagenation(pageNo, pageSize);
		}
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo",page.getNumber());
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("Totalelement",page.getTotalElements());
		model.addAttribute("Totalpages",page.getTotalPages());
		model.addAttribute("isFirst",page.isFirst());
		model.addAttribute("isLast",page.isLast());
		
		
		return "admin/viewproducts";
	}

	@GetMapping("/pdelete/{id}")
	public String productdelete(@PathVariable int id, HttpSession session) {
		try {
			pservice.deleteproduct(id);
			session.setAttribute("succMsg", "delete product sucessfully");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("errorMsg", "failed to delete product!, try again!");
		}
		return "redirect:/admin/products";
	}

	@GetMapping("/pedit/{id}")
	public String editproduct(@PathVariable int id, Model model) {
		model.addAttribute("command", pservice.getproduct(id));
		model.addAttribute("catt", service.getallcategory());
		return "admin/editproduct";
	}

	@PostMapping("/updateproducts")
	public String upadteproduct(@ModelAttribute product product, HttpSession session,
			@RequestParam("file") MultipartFile image) throws IOException {

		if (product.getDiscount() < 0 || product.getDiscount() > 100) {
			session.setAttribute("errorMsg", "Invalid discount");
		} else {
			product updateproduct = pservice.updateproduct(product, image);
			if (!ObjectUtils.isEmpty(updateproduct)) {
				session.setAttribute("succMsg", "update product succesfully");
			} else {
				session.setAttribute("errorMsg", "something wrong in server");
			}
		}
		return "redirect:/admin/products";

	}

	@GetMapping("/users")
	public String getalluser(Model m,@RequestParam Integer type) {
		List<userdetails> users=null;
		if(type==1) {
			users = userservice.getusers("ROLE_USER");
		}else {
			users = userservice.getusers("ROLE_ADMIN");
		}
		m.addAttribute("userType",type);
		m.addAttribute("users", users);
		return "admin/user";
	}

	@GetMapping("/updateStatus")
	public String Updateaccstatus(@RequestParam Boolean status, @RequestParam Integer id, HttpSession session,@RequestParam Integer type) {
		Boolean B = userservice.UpdateaccountStatus(id, status);
		if (B) {
			session.setAttribute("succMsg", "Account Status updated");
		} else {
			session.setAttribute("errorMsg", "Something Wrong on Server");
		}
		return "redirect:/admin/users?type="+type;
	}

	@GetMapping("/orderss")
	public String getallorders(Model m,
			@RequestParam(name = "pageNo",defaultValue = "0")Integer pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize) {
		/*List<productOrder> getallOrders = productOrderservice.getallOrders();
		m.addAttribute("orders", getallOrders);
		m.addAttribute("srch", false);
*/
		Page<productOrder> page = productOrderservice.getallOrdersPagenation(pageNo, pageSize);
		m.addAttribute("orders", page.getContent());
		m.addAttribute("srch", false);
		m.addAttribute("pageNo",page.getNumber());
		m.addAttribute("pageSize",pageSize);
		m.addAttribute("Totalelement",page.getTotalElements());
		m.addAttribute("Totalpages",page.getTotalPages());
		m.addAttribute("isFirst",page.isFirst());
		m.addAttribute("isLast",page.isLast());
		
		return "admin/Orders";
	}

	@PostMapping("/update-order-status")
	public String UpdateOrderStatus(@RequestParam Integer id, @RequestParam Integer st, HttpSession session) {
		OrderStatus[] values = OrderStatus.values();
		String status = null;
		for (OrderStatus orderst : values) {
			if (orderst.getId().equals(st)) {
				status = orderst.getName();
			}
		}
		productOrder updateOrderStatus = productOrderservice.updateOrderStatus(id, status);

		try {
			commonUtil.sendMailforproductorder(updateOrderStatus, status);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}

		if (!ObjectUtils.isEmpty(updateOrderStatus)) {
			session.setAttribute("succMsg", "updated sucessfully");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "redirect:/admin/orderss";
	}

	@GetMapping("/search-order")
	public String Searchproduct(@RequestParam String orderid, Model m, HttpSession session,
			@RequestParam(name = "pageNo",defaultValue = "0")Integer pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize) {

		if (orderid != null && orderid.length() > 0) {

			productOrder order = productOrderservice.getOrdersByOrderid(orderid.trim());
			if (ObjectUtils.isEmpty(order)) {
				session.setAttribute("errorMsg", "Invalid UserId");
				m.addAttribute("orderDtls", null);
			} else {
				m.addAttribute("orderDtls", order);
			}
			m.addAttribute("srch", true);
		} else {
			/*List<productOrder> getallOrders = productOrderservice.getallOrders();
			m.addAttribute("orders", getallOrders);
			m.addAttribute("srch", false);*/
			Page<productOrder> page = productOrderservice.getallOrdersPagenation(pageNo, pageSize);
			m.addAttribute("orders", page.getContent());
			m.addAttribute("srch", false);
			
			m.addAttribute("pageNo",page.getNumber());
			m.addAttribute("pageSize",pageSize);
			m.addAttribute("Totalelement",page.getTotalElements());
			m.addAttribute("Totalpages",page.getTotalPages());
			m.addAttribute("isFirst",page.isFirst());
			m.addAttribute("isLast",page.isLast());
		}

		return "admin/Orders";
	}
	@GetMapping("/addadmin")
	public String loadAddAdmin() {
		return"admin/Add_Admin";
	}
	@PostMapping("/saveAdmin")
	public String saveadmins(@ModelAttribute userdetails user, HttpSession session,
			@RequestParam("file") MultipartFile file) throws IOException {
		String imagename = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		user.setProfileimage(imagename);
		userdetails saveUser = userservice.saveAdmin(user);
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
		
		return"redirect:/admin/addadmin";
	}
	

}
