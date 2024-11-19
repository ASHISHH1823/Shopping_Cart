package com.ashish.entity;
public class orderRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String mobileno;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String paymentType;
	public orderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	@Override
	public String toString() {
		return "orderRequest [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", mobileno="
				+ mobileno + ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", paymentType=" + paymentType + "]";
	}
	public orderRequest(String firstname, String lastname, String email, String mobileno, String address, String city,
			String state, String pincode, String paymentType) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileno = mobileno;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.paymentType = paymentType;
	}
	
	

}
