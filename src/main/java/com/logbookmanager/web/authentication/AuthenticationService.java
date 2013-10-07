package com.logbookmanager.web.authentication;

import java.io.Serializable;
/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller("authenticationController")
@RequestMapping(value = "/doLogin")
public class AuthenticationService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class.getName());

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(LoginCredentials loginCredentials) throws IncorrectLoginCredentialsException {
		logger.info("doLogin");

		try {
			
		}
		catch(Throwable t) {
			throw new IncorrectLoginCredentialsException();
		}


		
		// return the name of the View to be loaded
		return "loggedloggedIn";
	}
}
