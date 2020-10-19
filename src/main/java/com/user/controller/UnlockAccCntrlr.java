package com.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.user.domain.UnlockAccount;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class UnlockAccCntrlr {
	
	
	@Autowired
	private UserSrvc userSrvc;
	
	@GetMapping(AppConstants.LOAD_UNLOCK_ACC)
	public String loadUnlockAccFrm(@RequestParam("email") String email, Model model) {
		UnlockAccount unlockAcc = new UnlockAccount();
		unlockAcc.setEmail(email);
		model.addAttribute("unlockAcc",unlockAcc);
		return AppConstants.VIEW_UNLOCKACC;
	}
	
	@PostMapping(AppConstants.UNLOCK_ACC)
	public String handleUnlockAccSbmtBtn(@ModelAttribute("unlockAcc") HttpServletRequest req,Model model) {
		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		boolean isValid = userSrvc.isTempPswdValid(email,pswd);
		if(isValid) {
			userSrvc.unlockAcc(email, pswd);
			model.addAttribute(AppConstants.VIEW_SUCC_MSG,AppConstants.UNLOCK_SUCC_MSG);
		}else {
			model.addAttribute(AppConstants.VIEW_FAIL_MSG,AppConstants.UNLOCK_FAIL_MSG);
		}
		
		return AppConstants.VIEW_UNLOCKACC;
	}
	

}
