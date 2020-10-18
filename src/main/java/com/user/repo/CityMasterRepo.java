package com.user.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.CityMasterEntity;
import com.user.entity.StateMasterEntity;

public interface CityMasterRepo   extends JpaRepository<CityMasterEntity, Serializable>{
	
	public List<CityMasterEntity> findByStateD(Integer stateId);

}
