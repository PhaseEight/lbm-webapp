package com.logbookmanager.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.logbookmanager.domain.model.security.SecureUser;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.RegisteredUserService;

/**
 * 
 * SecureUser Managed Bean
 * 
 * @author Peter Neil
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
@Component
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Spring SecureUser Service is injected...
	@ManagedProperty(value = "#{registeredUserService}")
	RegisteredUserService registeredUserService;

	private List<SecureUser> userList;

	private Long id;
	private String name;
	private String surname;

	/**
	 * Add SecureUser
	 * 
	 * @return String - Response Message
	 */
	public boolean addUser() {
		SecureUser secureUser = new SecureUser(new UserName(getName() + "." + getSurname()));
		getUserService().save(secureUser);
		return true;

	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId(null);
		this.setName("");
		this.setSurname("");
	}

	/**
	 * Get SecureUser List
	 * 
	 * @return List - SecureUser List
	 */
	public List<SecureUser> getUserList() {
		userList = new ArrayList<SecureUser>();
		userList.addAll(getUserService().findAll());
		return userList;
	}

	/**
	 * Get SecureUser Service
	 * 
	 * @return IuserService - SecureUser Service
	 */
	public RegisteredUserService getUserService() {
		return registeredUserService;
	}

	/**
	 * Set SecureUser Service
	 * 
	 * @param IuserService
	 *            - SecureUser Service
	 */
	public void setUserService(RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	/**
	 * Set SecureUser List
	 * 
	 * @param List
	 *            - SecureUser List
	 */
	public void setUserList(List<SecureUser> userList) {
		this.userList = userList;
	}

	/**
	 * Get SecureUser Id
	 * 
	 * @return int - SecureUser Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set SecureUser Id
	 * 
	 * @param int - SecureUser Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get SecureUser Name
	 * 
	 * @return String - SecureUser Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set SecureUser Name
	 * 
	 * @param String
	 *            - SecureUser Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get SecureUser Surname
	 * 
	 * @return String - SecureUser Surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set SecureUser Surname
	 * 
	 * @param String
	 *            - SecureUser Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

}