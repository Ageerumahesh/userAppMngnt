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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUnique(String email) {
		UserAccountEntity userEntitly = userAccRepo.findByEmail(email);
		return userEntitly != null ? true : false;
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
		List<StateMasterEntity> statesEntityList = stateRepo.findByCntryD(cntryId);
		Map<Integer, String> statesMap = new HashMap<Integer, String>();
		statesEntityList.forEach(entity -> {
			statesMap.put(entity.getStateId(), entity.getStateNm());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCityByStateId(Integer stateId) {
		List<CityMasterEntity> citiesEntityList = cityRepo.findByStateD(stateId);
		Map<Integer, String> citiesMap = new HashMap<Integer, String>();
		citiesEntityList.forEach(entity -> {
			citiesMap.put(entity.getCityId(), entity.getCityNm());
		});
		return citiesMap;
	}

	@Override
	public boolean saveUserAccount(UserAccountModel usrMdl) {
		usrMdl.setAccStatus("Locked");
		usrMdl.setPswd(generateTempPswd());
		usrMdl.setStateD(1);
		usrMdl.setCityD(1);
		UserAccountEntity userAccEntity = new UserAccountEntity();
		BeanUtils.copyProperties(usrMdl, userAccEntity);
		UserAccountEntity resUserEntity = userAccRepo.save(userAccEntity);

		String to = resUserEntity.getEmail();
		String subject = "Unlock Account|AshokIt";
		String body = getSuccMailBody(usrMdl);
		boolean isEmailSend = sendRegSuccEmail(to, subject, body);
		return isEmailSend;
	}

	@Override
	public String getSuccMailBody(UserAccountModel usrMdl) {
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		Path path = Paths.get(fileName,"");
		String body = null;
		try {
			Stream<String> lines = Files.lines(path);
			List<String> replaced = lines
					.map(line -> line.replace("{FNAME}", usrMdl.getFrstNm()).replace("{LNAME}", usrMdl.getLstNm())
							.replace("{TEMP-PWD}", usrMdl.getPswd()).replace("{EMAIL}", usrMdl.getEmail()))
					.collect(Collectors.toList());
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
	public String isTempPswdValid(String tempPswd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unlockAcc(String email, String pswd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String rcvryPswd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRcvryPswdEmailBody(UserAccountModel usrMdl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendPswdToEmail(String pswd, String subject, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateTempPswd() {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

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
