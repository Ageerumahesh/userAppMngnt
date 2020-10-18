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
public class LoginCntrlr {
	
	
	@Autowired
	private UserSrvc usrSrvc;
	
	
	@GetMapping(AppConstants.INDEX_PAGE)
	public String indexPage() {
		return "login";
	}
	
	@PostMapping(AppConstants.USER_LOGIN)
	public String loginUsr(HttpServletRequest req , Model model) {
	
	//todo to write logic for valdn	
	 return "view name";
	}
}
