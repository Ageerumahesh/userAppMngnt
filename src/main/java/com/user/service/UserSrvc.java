package com.user.service;

import java.util.List;
import java.util.Map;

import com.user.domain.UserAccountModel;

public interface UserSrvc {

//login screen
	public String loginCheck(String email, String pswd);

	// onclick on sign up go to registn screen

	public boolean isUnique(String email);
	
	public String generateTempPswd();

	public Map<Integer, String> loadCntryData();

	public Map<Integer, String> loadStateByCntryId(Integer cntryId);

	public Map<Integer, String> loadCityByStateId(Integer stateId);
	
	public boolean saveUserAccount(UserAccountModel usrMdl);
	
	public String getSuccMailBody(UserAccountModel usrMdl);
	
    public boolean sendRegSuccEmail(String to,String subject,String body);
    
    public String isTempPswdValid(String tempPswd);
    
    public boolean unlockAcc(String email,String pswd);
    
    public String rcvryPswd(String email);
    
    public String getRcvryPswdEmailBody(UserAccountModel usrMdl);
    
    public String sendPswdToEmail(String pswd , String subject,String body);
    
    
    
    
    
    
    
	
}
