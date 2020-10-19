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
public class LoginCntrlr {

	@Autowired
	private UserSrvc usrSrvc;

	@GetMapping(AppConstants.INDEX_PAGE)
	public String indexPage() {
		return AppConstants.VIEW_LOGIN;
	}

	@PostMapping(AppConstants.USER_LOGIN)
	public String loginUsr(HttpServletRequest req, Model model) {

		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		String res = usrSrvc.loginCheck(email, pswd);
		if (res.equals("InvalidCredentials")) {
			model.addAttribute("inValidCrdntls", AppConstants.INVALD_MSG);
		} else if (res.equals("locked")) {
			model.addAttribute("locked", AppConstants.LOCKED_MSG);
		}
		return AppConstants.VIEW_DASHBOARD;
	}
}
