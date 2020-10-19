package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.user.controller.FrgtPswdCntrlr;
import com.user.controller.LoginCntrlr;
import com.user.controller.UnlockAccCntrlr;
import com.user.controller.UserRegCntrlr;




//@Configuration
//@ComponentScan(basePackages= {"com.user.util.EmailUtils"},
//basePackageClasses= {FrgtPswdCntrlr.class,LoginCntrlr.class,UnlockAccCntrlr.class,UserRegCntrlr.class})
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
