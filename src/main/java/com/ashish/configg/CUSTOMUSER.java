package com.ashish.configg;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ashish.entity.userdetails;

public class CUSTOMUSER implements UserDetails{
	
	private userdetails userdetails;
	

	public CUSTOMUSER(userdetails userdetails) {
		super();
		this.userdetails = userdetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(userdetails.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return userdetails.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userdetails.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return userdetails.getAccountNonlocked();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return userdetails.getIsEnable();
	}

}
