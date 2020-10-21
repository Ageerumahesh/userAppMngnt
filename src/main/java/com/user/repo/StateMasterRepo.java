package com.user.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.StateMasterEntity;

public interface StateMasterRepo  extends JpaRepository<StateMasterEntity, Serializable>{
	
	public List<StateMasterEntity> findByCntryID(Integer cntyId);

}
