package com.ashish.configg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ashish.entity.userdetails;
import com.ashish.repo.userrepo;

@Service
public class CustomuserDetailsService implements UserDetailsService {
	
	@Autowired
	private userrepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userdetails userdetails=userRepo.findByemail(username);
		if(userdetails == null) {
			throw new UsernameNotFoundException("user not found");
		}
		else {
			return new CUSTOMUSER(userdetails);
		}
	}

}
