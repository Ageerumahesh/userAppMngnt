package com.user.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data 
@Entity
@Table(name = "UserAccount") 
public class UserAccountEntity {

@Id
@Column(name = "tciUsrId") 
@GeneratedValue
private Integer userId;

@Column(name = "tcsUsrAccStatus") 
private String accStatus;

@Column(name = "tcsUsrFrstNm") 
private String frstNm;

@Column(name = "tcsUsrLstNm") 
private String lstNm;

@Column(name = "tcsUsrPswd") 
private String pswd;

@Column(name = "tcsUsrEmail",unique=true) 
private String email;

@Column(name = "tcsUsrNum") 
private String num;

@Column(name = "tcsUsrDob") 
private LocalDate dob;



@Column(name = "tcsUsrId") 
private Character gndr;

@Column(name = "tciUsrCntryD") 
private int cntryD;

@Column(name = "tcsUsrStateD") 
private int stateD;
@Column(name = "tcsUsrCityD") 
private int cityD;


}
