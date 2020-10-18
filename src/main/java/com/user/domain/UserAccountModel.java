package com.user.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserAccountModel {

private String accStatus;
private Integer userId;
private String frstNm;
private String lstNm;
private String pswd;
private String email;
private String num;
private String Dob;
private Character gndr;
private Integer CntryD;
private Integer StateD;
private Integer CityD;


}
