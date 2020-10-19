package com.user.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.UserAccountEntity;


public interface UserAccountRepo extends JpaRepository<UserAccountEntity, Serializable> {
	
	public UserAccountEntity findByEmail(String email);
	public UserAccountEntity findByEmailAndPswd(String email,String pswd);

}
