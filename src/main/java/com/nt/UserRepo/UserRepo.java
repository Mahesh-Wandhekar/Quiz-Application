package com.nt.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Entity.UserEntity;

public interface UserRepo  extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByEmail(String email);
	
	public UserEntity findByEmailAndPassword(String email,String password);
	public UserEntity findByEmailAndRole(String email,String role);

}
