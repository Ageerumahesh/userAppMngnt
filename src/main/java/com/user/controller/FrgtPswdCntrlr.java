package com.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class FrgtPswdCntrlr {

	@Autowired
	private UserSrvc userSrvc;

	@GetMapping(value = AppConstants.FRGTPSWD_PAGE)
	public String loadFrgtPswdFrm() {
		return AppConstants.VIEW_FRGTPSWD;
	}

	@PostMapping(value = AppConstants.FRGT_PSWD)
	public String handleFrgtPswdSubmtBtn(HttpServletRequest req, Model model) {
		model.addAttribute(AppConstants.VIEW_SUCC_MSG, userSrvc.rcvryPswd(req.getParameter(AppConstants.EMAIL)));
		return AppConstants.VIEW_FRGTPSWD;
	}

}
