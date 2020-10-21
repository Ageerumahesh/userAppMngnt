package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.user.domain.UnlockAccount;
import com.user.props.AppProperties;
import com.user.res.AppConstants;
import com.user.service.UserSrvc;

@Controller
public class UnlockAccCntrlr {

	@Autowired
	private UserSrvc userSrvc;
	
	@Autowired
	private AppProperties props;

	@GetMapping(AppConstants.LOAD_UNLOCK_ACC)
	public String loadUnlockAccFrm(@RequestParam(AppConstants.EMAIL) String email, Model model) {
		UnlockAccount unlockAcc = new UnlockAccount();
		unlockAcc.setEmail(email);
		model.addAttribute(AppConstants.UNLOCKACC, unlockAcc);
		return AppConstants.VIEW_UNLOCKACC;
	}

	@PostMapping(AppConstants.UNLOCK_ACC)
	public String handleUnlockAccSbmtBtn(@ModelAttribute(AppConstants.UNLOCKACC) UnlockAccount unLockAcc, Model model) {
		boolean isValid = userSrvc.isTempPswdValid(unLockAcc.getEmail(), unLockAcc.getTemPswd());
		if (isValid) {
			userSrvc.unlockAcc(unLockAcc.getEmail(), unLockAcc.getPswd());
			model.addAttribute(AppConstants.VIEW_SUCC_MSG, props.getMessages().get("UNLOCK_SUCC_MSG"));
		} else {
			model.addAttribute(AppConstants.VIEW_FAIL_MSG, props.getMessages().get("UNLOCK_FAIL_MSG"));
		}

		return AppConstants.VIEW_UNLOCKACC;
	}

}
