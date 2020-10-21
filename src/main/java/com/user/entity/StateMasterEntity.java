package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATES_MASTER")
public class StateMasterEntity {

	@Id
	@GeneratedValue
	@Column(name = "tciStateId")
	private Integer stateId;

	@Column(name = "tciCntryID")
	private Integer cntryID;

	@Column(name = "tcsStateNm")
	private String stateNm;

}
