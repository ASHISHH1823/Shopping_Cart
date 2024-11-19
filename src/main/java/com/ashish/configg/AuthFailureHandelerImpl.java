package com.ashish.configg;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ashish.entity.userdetails;
import com.ashish.repo.userrepo;
import com.ashish.service.userservice;
import com.ashish.util.Appconstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandelerImpl extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private userservice userservice;

	@Autowired
	private userrepo repo;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String email = request.getParameter("username");
		userdetails userdts = repo.findByemail(email);
if(userdts!=null) {
		if (userdts.getIsEnable()) {
			if (userdts.getAccountNonlocked()) {
				if (userdts.getFailedAttempt() < Appconstant.ATTEMPT_TIME) {
					userservice.increaseFaildAttempt(userdts);
				} else {
					userservice.useraccountLock(userdts);
					exception = new LockedException("Your Account is blocked Try after some time!!");
				}
			} else {
				if (userservice.unlockAccountTimeExpire(userdts)) {
					exception = new LockedException("Your Account Unlocked");
				} else {
					exception = new LockedException("Your Account is Locked");
				}
			}

		} else {
			exception = new LockedException("Your Account is InActive");
		}			
}
else {
	exception = new LockedException("Invalid email or password!");
}
		super.setDefaultFailureUrl("/signin?error");

		super.onAuthenticationFailure(request, response, exception);
	}

}
