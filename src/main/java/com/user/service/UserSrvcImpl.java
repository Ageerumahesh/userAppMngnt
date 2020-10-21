package com.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.domain.UserAccountModel;
import com.user.entity.CityMasterEntity;
import com.user.entity.CntryMasterEntity;
import com.user.entity.StateMasterEntity;
import com.user.entity.UserAccountEntity;
import com.user.repo.CityMasterRepo;
import com.user.repo.CntryMasterRepo;
import com.user.repo.StateMasterRepo;
import com.user.repo.UserAccountRepo;
import com.user.res.AppConstants;
import com.user.util.EmailUtils;

@Service
public class UserSrvcImpl implements UserSrvc {

	@Autowired
	private UserAccountRepo userAccRepo;

	@Autowired
	private CntryMasterRepo cntryRepo;

	@Autowired
	private StateMasterRepo stateRepo;

	@Autowired
	private CityMasterRepo cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(String email, String pswd) {
		UserAccountEntity userEntitly = userAccRepo.findByEmailAndPswd(email, pswd);
		if (userEntitly == null) {
			return AppConstants.INVALID;
		} else if (userEntitly.getAccStatus().equals(AppConstants.LOCKED)) {
			return AppConstants.LOCKED;
		}
		return AppConstants.SUCCESS;
	}

	@Override
	public boolean isUnique(String email) {
		UserAccountEntity userEntity = userAccRepo.findByEmail(email);
		return userEntity != null ? true : false;
	}

	@Override
	public Map<Integer, String> loadCntryData() {
		List<CntryMasterEntity> entitesList = cntryRepo.findAll();
		Map<Integer, String> cntryMap = new HashMap<Integer, String>();
		entitesList.forEach(entity -> {
			cntryMap.put(entity.getCntryId(), entity.getCntryNm());
		});
		return cntryMap;
	}

	@Override
	public Map<Integer, String> loadStateByCntryId(Integer cntryId) {
		List<StateMasterEntity> statesEntityList = stateRepo.findByCntryID(cntryId);
		Map<Integer, String> statesMap = new HashMap<Integer, String>();
		statesEntityList.forEach(entity -> {
			statesMap.put(entity.getStateId(), entity.getStateNm());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCityByStateId(Integer stateId) {
		List<CityMasterEntity> citiesEntityList = cityRepo.findByStateID(stateId);
		Map<Integer, String> citiesMap = new HashMap<Integer, String>();
		citiesEntityList.forEach(entity -> {
			citiesMap.put(entity.getCityId(), entity.getCityNm());
		});
		return citiesMap;
	}

	@Override
	public boolean saveUserAccount(UserAccountModel usrMdl) {
		usrMdl.setAccStatus(AppConstants.LOCKED);
		usrMdl.setPswd(generateTempPswd());
		UserAccountEntity userAccEntity = new UserAccountEntity();
		BeanUtils.copyProperties(usrMdl, userAccEntity);
		UserAccountEntity resUserEntity = userAccRepo.save(userAccEntity);

		String to = resUserEntity.getEmail();
		String subject = AppConstants.EMAIL_SUBJECT;
		String body = getSuccMailBody(usrMdl);
		boolean isEmailSend = sendRegSuccEmail(to, subject, body);
		return isEmailSend;
	}

	@Override
	public String getSuccMailBody(UserAccountModel usrMdl) {
		String fileName = AppConstants.EMAIL_TMPLT_FILENAME;
		Path path = Paths.get(fileName, "");
		String body = null;
		try {
			Stream<String> lines = Files.lines(path);
			List<String> replaced = lines.map(line -> line.replace(AppConstants.RPLSMNT_VAR_FN, usrMdl.getFrstNm())
					.replace(AppConstants.RPLSMNT_VAR_LN, usrMdl.getLstNm())
					.replace(AppConstants.RPLSMNT_VAR_TEMP_PSWD, usrMdl.getPswd())
					.replace(AppConstants.RPLSMNT_VAR_EMAIL, usrMdl.getEmail())).collect(Collectors.toList());
			body = String.join("", replaced);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	@Override
	public boolean sendRegSuccEmail(String to, String subject, String body) {
		return emailUtils.sendEmail(to, subject, body);
	}

	@Override
	public boolean isTempPswdValid(String email, String pswd) {
		UserAccountEntity userEntity = userAccRepo.findByEmailAndPswd(email, pswd);
		return userEntity != null ? true : false;
	}

	@Override
	public boolean unlockAcc(String email, String pswd) {
		UserAccountEntity userAccEntity = userAccRepo.findByEmail(email);
		userAccEntity.setAccStatus(AppConstants.UNLOCKED);
		userAccEntity.setPswd(pswd);
		userAccRepo.save(userAccEntity);
		return true;
	}

	@Override
	public String rcvryPswd(String email) {
		UserAccountEntity userEntity = userAccRepo.findByEmail(email);
		if (userEntity == null) {
			return AppConstants.RCVRY_EMAIL_INVALID;
		}
		String to = email;
		String subject = AppConstants.REVRY_EMAIL_SUBJECT;
		UserAccountModel usrMdl = new UserAccountModel();
		BeanUtils.copyProperties(userEntity, usrMdl);
		String body = getRcvryPswdEmailBody(usrMdl);
		boolean isEmail = sendPswdToEmail(to, subject, body);
		return isEmail ? AppConstants.RCVRY_SUCC_MSG : AppConstants.RCVRY_FAIL_MSG;
	}

	@Override
	public String getRcvryPswdEmailBody(UserAccountModel usrMdl) {
		String fileName = AppConstants.EMAIL_REVRY_FILENAME;
		Path path = Paths.get(fileName, "");
		String body = null;
		Stream<String> lines = null;
		List<String> replaced = null;
		try {
			lines = Files.lines(path);
			replaced = lines.map(line -> line.replace(AppConstants.RPLSMNT_VAR_FN, usrMdl.getFrstNm())
					.replace(AppConstants.RPLSMNT_VAR_LN, usrMdl.getLstNm())
					.replace(AppConstants.RPLSMNT_VAR_PSWD, usrMdl.getPswd()))
					.collect(Collectors.toList());
			body = String.join("", replaced);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	@Override
	public boolean sendPswdToEmail(String to, String subject, String body) {
		emailUtils.sendEmail(to, subject, body);
		return true;
	}

	@Override
	public String generateTempPswd() {
		// chose a Character random from this String
		String AlphaNumericString = AppConstants.ALPHBT_UPPRCS + AppConstants.TEMP_INTEGERS
				+ AppConstants.ALPHBT_UPPRCS;

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}
