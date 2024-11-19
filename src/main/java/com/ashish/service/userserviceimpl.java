package com.ashish.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.entity.userdetails;
import com.ashish.repo.userrepo;
import com.ashish.util.Appconstant;

@Service
@Transactional
public class userserviceimpl implements userservice {

	@Autowired
	private userrepo repo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public userdetails saveuser(userdetails user) {
		user.setRole("ROLE_USER");
		user.setIsEnable(true);
		user.setAccountNonlocked(true);
		user.setFailedAttempt(0);

		String encodepassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodepassword);
		userdetails saveuser = repo.save(user);
		return saveuser;
	}

	@Override
	public userdetails saveAdmin(userdetails user) {
		user.setRole("ROLE_ADMIN");
		user.setIsEnable(true);
		user.setAccountNonlocked(true);
		user.setFailedAttempt(0);
		
		String enccodepassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(enccodepassword);
		userdetails saveuser = repo.save(user);
		return saveuser;
	}

	@Override
	public userdetails getuserByemail(String email) {

		userdetails byemail = repo.findByemail(email);

		return byemail;

	}

	@Override
	public List<userdetails> getusers(String role) {

		return repo.findByRole(role);
	}

	@Override
	public Boolean UpdateaccountStatus(Integer id, Boolean status) {
		Optional<userdetails> findbyname = repo.findById(id);
		if (findbyname.isPresent()) {
			userdetails userdetails = findbyname.get();
			userdetails.setIsEnable(status);
			repo.save(userdetails);
			return true;
		}
		return false;
	}

	@Override
	public void increaseFaildAttempt(userdetails user) {
		int attempt = user.getFailedAttempt() + 1;
		user.setFailedAttempt(attempt);
		repo.save(user);
	}

	@Override
	public void useraccountLock(userdetails user) {
		user.setAccountNonlocked(false);
		user.setLockTime(new Date());
		repo.save(user);

	}

	@Override
	public boolean unlockAccountTimeExpire(userdetails user) {
		long lockTime = user.getLockTime().getTime();
		long UnlockTime = lockTime + Appconstant.UNLOCK_DURATION_TIME;
		long currentTime = System.currentTimeMillis();
		if (UnlockTime < currentTime) {
			user.setAccountNonlocked(true);
			user.setFailedAttempt(0);
			user.setLockTime(null);
			repo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public void resetAttempt(int userid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void UpdateUserResetToken(String email, String resetToken) {
		userdetails byemail = repo.findByemail(email);
		byemail.setReset_token(resetToken);
		repo.save(byemail);
	}

	@Override
	public userdetails getUserByToken(String token) {
		return repo.findByresetToken(token);

	}

	@Override
	public userdetails updateUser(userdetails user) {
		return repo.save(user);
	}

	@Override
	public userdetails updateprofileuser(userdetails user, MultipartFile img) {
		userdetails dbuser = repo.findById(user.getId()).get();
		if(!img.isEmpty()) {
			dbuser.setProfileimage(img.getOriginalFilename());
		}
		if(!ObjectUtils.isEmpty(dbuser)) {
			dbuser.setName(user.getName());
			dbuser.setMobilenumber(user.getMobilenumber());
			dbuser.setAddress(user.getAddress());
			dbuser.setCity(user.getCity());
			dbuser.setPincode(user.getPincode());
			dbuser.setState(user.getState());
			dbuser=repo.save(dbuser);
		}
	try {
		if(!img.isEmpty()) {
			
			File savefile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "profile_img" + File.separator
					+ img.getOriginalFilename());
			Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}	
		return dbuser;
	}

	@Override
	public Boolean existEmail(String email) {
		
		return repo.existsByEmail(email);
	}

}
