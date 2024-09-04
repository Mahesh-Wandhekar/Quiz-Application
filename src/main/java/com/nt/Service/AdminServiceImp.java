package com.nt.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Entity.UserEntity;
import com.nt.UserRepo.UserRepo;
import com.nt.binding.LoginBind;


@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public boolean adminLogin(LoginBind loginbind) throws Exception {
		
		String pwd=loginbind.getPassword();
		MessageDigest msgDigest=MessageDigest.getInstance("SHA-256");
		msgDigest.reset();
		msgDigest.update(pwd.getBytes());
		byte[] msgpwd =msgDigest.digest();
		byte[] encodepwd=Base64.getEncoder().encode(msgpwd);
		
		
		UserEntity user=repo.findByEmailAndRole(loginbind.getEmail(), "ADMIN");
		
		if(user == null) {
			UserEntity user1=repo.findByEmailAndPassword(loginbind.getEmail(),new String(encodepwd));
		if(user1== null) {	
		return false;
		}
		return false;
		}
		return true;
	}
}
