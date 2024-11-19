package com.ashish.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ashish.entity.userdetails;

public interface userservice {
	public userdetails saveuser(userdetails user);
	
	public userdetails saveAdmin(userdetails user);
	
	public userdetails getuserByemail(String email);
	
	public List<userdetails> getusers(String role);
	
	public Boolean UpdateaccountStatus(Integer id,Boolean status);
	
	public void increaseFaildAttempt(userdetails user);
	
	public void useraccountLock(userdetails user);
	
	public boolean unlockAccountTimeExpire(userdetails user);
	
	public void resetAttempt(int userid);
	
	public void UpdateUserResetToken(String email, String resetToken);
	
	public userdetails getUserByToken(String token);
	
	public userdetails updateUser(userdetails user);
	
	public userdetails updateprofileuser(userdetails user,MultipartFile img);
	
	public Boolean existEmail(String email);


}
