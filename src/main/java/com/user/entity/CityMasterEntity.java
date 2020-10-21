package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIES_MASTER")
public class CityMasterEntity {

	@Id
	@GeneratedValue
	@Column(name = "tciCityId")
	private Integer cityId;

	@Column(name = "tcsCityNm")
	private String cityNm;

	@Column(name = "tciStateID")
	private Integer stateID;

}
