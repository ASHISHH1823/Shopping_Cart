package com.ashish.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class productOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String orderid;
	
	private LocalDate orderDate;
	
	@ManyToOne
	private product product;
	
	private Double price;
	
	private Integer quantity;
	
	private String status;
	
	@ManyToOne
	private userdetails user;
	
	private String paymentType;
	
	@OneToOne(cascade = CascadeType.ALL)
	private OrderAdress orderAdress;

	public productOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public userdetails getUser() {
		return user;
	}

	public void setUser(userdetails user) {
		this.user = user;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public OrderAdress getOrderAdress() {
		return orderAdress;
	}

	public void setOrderAdress(OrderAdress orderAdress) {
		this.orderAdress = orderAdress;
	}
	
	
	
	

}
