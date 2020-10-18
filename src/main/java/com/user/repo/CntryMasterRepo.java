package com.user.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.CntryMasterEntity;

public interface CntryMasterRepo extends JpaRepository<CntryMasterEntity, Serializable>{
	

}
