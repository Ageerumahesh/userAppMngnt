package com.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.user.domain.UserAccountModel;
import com.user.entity.CntryMasterEntity;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class UserRegCntrlr {
	
	@Autowired
	private UserSrvc userSrvc;
  
	
	
	@GetMapping(value = AppConstants.USER_REG)
	public String loadRegForm(Model model) {
		model.addAttribute("UsrAccMdl", new UserAccountModel());
		model.addAttribute("countries",userSrvc.loadCntryData());
		return AppConstants.VIEW_REGUSR;
	}
	
	//onchange on email we have chek email uniqueness
	@GetMapping(AppConstants.EMAIL_ISUNIQUE)
	public @ResponseBody String IsEmailUnique(@RequestParam("email") String email) {
		boolean isUnique = userSrvc.isUnique(email);
		return isUnique?"duplicate":"no";
	}
	
	
    //onchange of cntry dropodown we have to get state data onsccreen
	@GetMapping(AppConstants.CNTRY_CHNGE)
	public @ResponseBody Map<Integer,String> handleCntryChngeEvnt(@RequestParam("cntryId") Integer cntryId) {
		return userSrvc.loadStateByCntryId(cntryId);
	}
	
	//onchange of state  dropodown we have to get city data onsccreen
	@GetMapping(AppConstants.STATE_CHNGE)
	public @ResponseBody  Map<Integer,String> handleStateChngeEvnt(@RequestParam("stateId") Integer stateId){
		return userSrvc.loadCityByStateId(stateId);
	}
	
	
	@PostMapping(AppConstants.SAVE_USER)
	public String handeRgstrBtn(@ModelAttribute("UsrAccMdl") UserAccountModel usrMdl, Model model) {
		model.addAttribute("UsrAccMdl", new UserAccountModel());
		boolean res = userSrvc.saveUserAccount(usrMdl);
		if(res)
		{
			model.addAttribute("succMsg",AppConstants.SAVE_MSG);
		}else {
			model.addAttribute("failMsg",AppConstants.SAVE_FAIL);
		}
		return AppConstants.VIEW_REGUSR;
	}
	

}
