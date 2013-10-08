package com.logbookmanager.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.RegisteredUserService;

/**
 * 
 * RegisteredUser Managed Bean
 * 
 * @author Peter Neil
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
@Component
@Named("userBean")
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Spring RegisteredUser Service is injected...
	@ManagedProperty(value = "#{registeredUserService}")
	RegisteredUserService registeredUserService;

	private List<RegisteredUser> userList;

	private Long id;
	private String name;
	private String surname;

	/**
	 * Add RegisteredUser
	 * 
	 * @return String - Response Message
	 */
	public boolean addUser() {
		RegisteredUser registeredUser = new RegisteredUser(new UserName(getName() + "." + getSurname()));
		getUserService().save(registeredUser);
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
	 * Get RegisteredUser List
	 * 
	 * @return List - RegisteredUser List
	 */
	public List<RegisteredUser> getUserList() {
		userList = new ArrayList<RegisteredUser>();
		userList.addAll(getUserService().findAll());
		return userList;
	}

	/**
	 * Get RegisteredUser Service
	 * 
	 * @return IuserService - RegisteredUser Service
	 */
	public RegisteredUserService getUserService() {
		return registeredUserService;
	}

	/**
	 * Set RegisteredUser Service
	 * 
	 * @param IuserService
	 *            - RegisteredUser Service
	 */
	public void setUserService(RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	/**
	 * Set RegisteredUser List
	 * 
	 * @param List
	 *            - RegisteredUser List
	 */
	public void setUserList(List<RegisteredUser> userList) {
		this.userList = userList;
	}

	/**
	 * Get RegisteredUser Id
	 * 
	 * @return int - RegisteredUser Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set RegisteredUser Id
	 * 
	 * @param int - RegisteredUser Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get RegisteredUser Name
	 * 
	 * @return String - RegisteredUser Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set RegisteredUser Name
	 * 
	 * @param String
	 *            - RegisteredUser Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get RegisteredUser Surname
	 * 
	 * @return String - RegisteredUser Surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set RegisteredUser Surname
	 * 
	 * @param String
	 *            - RegisteredUser Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

}