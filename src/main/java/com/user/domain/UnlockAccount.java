package com.user.domain;

import lombok.Data;

@Data
public class UnlockAccount {

	private  String email;
	private  String temPswd;
	private  String pswd;
	private  String cnfrmPswd;
	
}
