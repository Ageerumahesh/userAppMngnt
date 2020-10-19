package com.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.user.domain.UserAccountModel;
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
		String email = req.getParameter("email");
		String succMsg = userSrvc.rcvryPswd(email);
		model.addAttribute(AppConstants.VIEW_SUCC_MSG,succMsg);
		return AppConstants.VIEW_FRGTPSWD;
	}

}
