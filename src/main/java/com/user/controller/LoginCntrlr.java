package com.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.props.AppProperties;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class LoginCntrlr {

	@Autowired
	private UserSrvc usrSrvc;
	
	@Autowired
	private AppProperties props;

	@GetMapping(AppConstants.INDEX_PAGE)
	public String indexPage() {
		return AppConstants.VIEW_LOGIN;
	}

	@PostMapping(AppConstants.USER_LOGIN)
	public String loginUsr(HttpServletRequest req, Model model) {

		String res = usrSrvc.loginCheck(req.getParameter(AppConstants.EMAIL), req.getParameter(AppConstants.PSWD));
		if (res.equals(AppConstants.INVALID)) {
			
			model.addAttribute(AppConstants.INVALID,props.getMessages().get("INVALID_MSG"));
		} else if (res.equals(AppConstants.LOCKED)) {
			model.addAttribute(AppConstants.LOCKED, props.getMessages().get("LOCKED_MSG"));
		} else {
			return AppConstants.VIEW_DASHBOARD;
		}
		return AppConstants.VIEW_LOGIN;
	}
}
