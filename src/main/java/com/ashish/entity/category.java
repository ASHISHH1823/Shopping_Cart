package com.ashish.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String imagename;
	private Boolean active;
	
	public category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public Boolean getisActive() {
		return active;
	}
	public void setActive(Boolean isActive) {
		this.active = isActive;
	}
	public category(int id, String name, String imagename, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.imagename = imagename;
		this.active = isActive;
	}
	@Override
	public String toString() {
		return "category [id=" + id + ", name=" + name + ", imagename=" + imagename + ", isActive=" + active + "]";
	}
	
	

}
