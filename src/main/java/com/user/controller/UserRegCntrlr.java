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
import com.user.props.AppProperties;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class UserRegCntrlr {

	@Autowired
	private UserSrvc userSrvc;
	
	@Autowired
	private AppProperties props;

	@GetMapping(value = AppConstants.USER_REG)
	public String loadRegForm(Model model) {
		model.addAttribute(AppConstants.USERACCMDL, new UserAccountModel());
		model.addAttribute(AppConstants.COUNTRIES, userSrvc.loadCntryData());
		return AppConstants.VIEW_REGUSR;
	}

	@GetMapping(AppConstants.EMAIL_ISUNIQUE)
	public @ResponseBody String IsEmailUnique(@RequestParam(AppConstants.EMAIL) String email) {
		boolean isUnique = userSrvc.isUnique(email);
		return isUnique ? AppConstants.DUPLICATE : AppConstants.NOT_DUPLICATE;
	}

	@GetMapping(AppConstants.CNTRY_CHNGE)
	public @ResponseBody Map<Integer, String> handleCntryChngeEvnt(
			@RequestParam(AppConstants.CNTRY_ID) Integer cntryId) {
		return userSrvc.loadStateByCntryId(cntryId);
	}

	@GetMapping(AppConstants.STATE_CHNGE)
	public @ResponseBody Map<Integer, String> handleStateChngeEvnt(
			@RequestParam(AppConstants.STATE_ID) Integer stateId) {
		return userSrvc.loadCityByStateId(stateId);
	}

	@PostMapping(AppConstants.SAVE_USER)
	public String handeRgstrBtn(@ModelAttribute(AppConstants.USERACCMDL) UserAccountModel usrMdl, Model model) {
		model.addAttribute("UsrAccMdl", new UserAccountModel());
		boolean res = userSrvc.saveUserAccount(usrMdl);
		if (res) {
			model.addAttribute(AppConstants.VIEW_SUCC_MSG, props.getMessages().get("SAVE_MSG"));
		} else {
			model.addAttribute(AppConstants.VIEW_SUCC_MSG, props.getMessages().get("SAVE_FAIL"));
		}
		return AppConstants.VIEW_REGUSR;
	}

}
