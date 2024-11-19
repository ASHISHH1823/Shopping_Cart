package com.ashish.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 500)
	private String title;
	
	@Column(length = 5000)
	private String description;
	
	private String category;
	private double price;
	private int stock;
	private String imagename;
	private int discount;
	private double discountprice;
	private Boolean isactive;
	
	
	public product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public product(Boolean isactive) {
		super();
		this.isactive = isactive;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public product(int discount, double discountprice) {
		super();
		this.discount = discount;
		this.discountprice = discountprice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(double discountprice) {
		this.discountprice = discountprice;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public product(int id, String title, String description, String category, double price, int stock,
			String imagename) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.imagename = imagename;
	}
	@Override
	public String toString() {
		return "product [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", stock=" + stock + ", imagename=" + imagename + ", discount=" + discount
				+ ", discountprice=" + discountprice + ", isactive=" + isactive + "]";
	}

	
}
