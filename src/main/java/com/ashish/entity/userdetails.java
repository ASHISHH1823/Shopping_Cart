package com.ashish.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class userdetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String mobilenumber;
	private String email;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String password;
	private String profileimage;
	private String role;
	private Boolean isEnable;
	private Boolean accountNonlocked;
	private Integer failedAttempt;
	private Date lockTime;
	private String resetToken;
	
	
	
	
	
	
	public userdetails(Integer id, String name, String mobilenumber, String email, String address, String city,
			String state, String pincode, String password, String profileimage, String role, Boolean isEnable,
			Boolean accountNonlocked, Integer failedAttempt, Date lockTime, String reset_token) {
		super();
		this.id = id;
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.password = password;
		this.profileimage = profileimage;
		this.role = role;
		this.isEnable = isEnable;
		this.accountNonlocked = accountNonlocked;
		this.failedAttempt = failedAttempt;
		this.lockTime = lockTime;
		this.resetToken = resetToken;
	}

	public String getReset_token() {
		return resetToken;
	}

	public void setReset_token(String reset_token) {
		this.resetToken = resetToken;
	}

	

	public Boolean getAccountNonlocked() {
		return accountNonlocked;
	}

	public void setAccountNonlocked(Boolean accountNonlocked) {
		this.accountNonlocked = accountNonlocked;
	}

	public Integer getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(Integer failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public userdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	

	@Override
	public String toString() {
		return "userdetails [id=" + id + ", name=" + name + ", mobilenumber=" + mobilenumber + ", email=" + email
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", password=" + password + ", profileimage=" + profileimage + ", role=" + role + ", isEnable="
				+ isEnable + ", accountNonlocked=" + accountNonlocked + ", failedAttempt=" + failedAttempt
				+ ", lockTime=" + lockTime + ", resetToken=" + resetToken + "]";
	}
	
	

}
