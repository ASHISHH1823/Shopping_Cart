package com.ashish.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private userdetails user;
	@ManyToOne
	private product product;
	private Integer quantity;
	@Transient
	private Double totalprice;
	@Transient
	private Double totalorderprice;
	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Double getTotalorderprice() {
		return totalorderprice;
	}

	public void setTotalorderprice(Double totalorderprice) {
		this.totalorderprice = totalorderprice;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public userdetails getUser() {
		return user;
	}
	public void setUser(userdetails user) {
		this.user = user;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public cart(Integer id, userdetails user, com.ashish.entity.product product, Integer quantity, Double totalprice) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.totalprice = totalprice;
	}
	@Override
	public String toString() {
		return "cart [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + ", totalprice="
				+ totalprice + "]";
	}
	
	
	

}
