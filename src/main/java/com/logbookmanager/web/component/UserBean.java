package com.logbookmanager.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.logbookmanager.domain.model.security.User;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.UserService;

/**
 * 
 * User Managed Bean
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

	// Spring User Service is injected...
	@ManagedProperty(value = "#{userService}")
	UserService userService;

	private List<User> userList;

	private Long id;
	private String name;
	private String surname;

	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public boolean addUser() {
		User user = new User(new UserName(getName() + "." + getSurname()));
		getUserService().save(user);
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
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().findAll());
		return userList;
	}

	/**
	 * Get User Service
	 * 
	 * @return IuserService - User Service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param IuserService
	 *            - User Service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Set User List
	 * 
	 * @param List
	 *            - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set User Name
	 * 
	 * @param String
	 *            - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set User Surname
	 * 
	 * @param String
	 *            - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

}