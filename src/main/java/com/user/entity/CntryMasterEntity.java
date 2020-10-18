package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COUNTRY_MASTER")
public class CntryMasterEntity {



@Id
@GeneratedValue
@Column(name = "tciCntryId") 
private Integer cntryId;

@Column(name = "tcsCntryCode") 
private String cntryCode;

@Column(name = "tcsCntryNm") 
private String cntryNm;


}
