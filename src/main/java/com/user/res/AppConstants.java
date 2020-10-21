package com.user.res;

public class AppConstants {

	public static final String INDEX_PAGE = "/";
	public static final String SAVE_USER = "/saveUsr";
	public static final String UPDATE_USER = "/updateUsr";
	public static final String USER_LOGIN = "/loginUsr";
	public static final String USER_REG = "/regUsr";
	public static final String FRGT_PSWD = "/rcvryEmail";
	
	
	public static final String UPDATE_MSG = "User updated sucessfully";
	public static final String FRGTPSWD_PAGE = "/frgtPswd";
	public static final String REGUSER_PAGE = "/regUsr";
	public static final String INVALID_USR_MSG = "Invalid credentials";
	public static final String USER_LOCKED = "User locked";
	public static final String EMAIL_ISUNIQUE = "/isEmailIUnique";
	public static final String CNTRY_CHNGE = "/cntryChngeEvnt";
	public static final String STATE_CHNGE = "/stateChngeEvnt";
	public static final String LOAD_UNLOCK_ACC = "/loadUnLockAcc";
	public static final String UNLOCK_ACC = "/unLockAcc";
	
	
	
	
	public static final String SUCCC_MSG = "Login sucessfully";

	// msg
	public static final String VIEW_SUCC_MSG = "succMsg";
	public static final String VIEW_FAIL_MSG = "failMsg";

	// recovery messages
	public static final String RCVRY_SUCC_MSG = "Password sent to email";
	public static final String RCVRY_FAIL_MSG = "Email not sent";
	public static final String RCVRY_EMAIL_INVALID = "Please , enter correct email";

	// view name
	public static final String VIEW_DASHBOARD = "dashboard";
	public static final String VIEW_FRGTPSWD = "frgtPswd";
	public static final String VIEW_LOGIN = "login";
	public static final String VIEW_REGUSR = "regUsr";
	public static final String VIEW_UNLOCKACC = "unlockAcc";
	public static final String VIEW_WELCOME = "welcome";

	// strig letrals
	public static final String INVALID = "invalid";
	public static final String LOCKED = "locked";
	public static final String UNLOCKED     = "unlocked";
	public static final String SUCCESS = "success";
	public static final String EMAIL = "email";
	public static final String PSWD = "pswd";
	public static final String DUPLICATE = "duplicate";
	public static final String NOT_DUPLICATE = "notDuplicate";
	public static final String UNLOCKACC = "unlockAcc";

	// req param string litralss
	public static final String STATE_ID = "stateId";
	public static final String CNTRY_ID = "cntryId";
	public static final String CITY_ID = "cityId";
	public static final String COUNTRIES = "countries";
	public static final String USERACCMDL = "usrAccMdl";
	

	// email relates litrals
	public static final String EMAIL_SUBJECT = "Unlock Account | AshokIt";
	public static final String REVRY_EMAIL_SUBJECT ="RECOVERY PASSWORD EMAIL";
	public static final String EMAIL_TMPLT_FILENAME = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
	public static final String EMAIL_REVRY_FILENAME = "RCVRY-ACC-EMAIL-PWD-BODY-TEMPLATE.txt";

	// CONSTANTS IN EMAIL BODY TEMPT

	public static final String RPLSMNT_VAR_FN = "{FNAME}";
	public static final String RPLSMNT_VAR_LN = "{LNAME}";
	public static final String RPLSMNT_VAR_TEMP_PSWD = "{TEMP-PWD}";
	public static final String RPLSMNT_VAR_EMAIL = "{EMAIL}";
	public static final String RPLSMNT_VAR_PSWD = "{PWD}";
	
	//litrals for temp pswd genration
	
	public static final String ALPHBT_UPPRCS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ALPHBT_LWRCS = "abcdefghijklmnopqrstuvxyz";
	public static final String TEMP_INTEGERS  = "0123456789";
	

}
