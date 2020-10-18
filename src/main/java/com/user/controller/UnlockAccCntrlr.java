package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.domain.UnlockAccount;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class UnlockAccCntrlr {
	
	
	@Autowired
	private UserSrvc userSrvc;
	
	@GetMapping(AppConstants.LOAD_UNLOCK_ACC)
	public String loadUnlockAccFrm(@RequestParam("email") String email, Model model) {
		model.addAttribute("email",email);
		
		
		return "";
	}
	
	@PostMapping(AppConstants.UNLOCK_ACC)
	public String handleUnlockAccSbmtBtn(@ModelAttribute("UnlockAcc") UnlockAccount unLockAcc) {
		
		//model.addAttribute(attributeValue)
		return "";
	}
	

}
