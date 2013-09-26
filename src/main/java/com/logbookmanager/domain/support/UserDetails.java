package com.logbookmanager.domain.support;


public class UserDetails extends ValueObjectSupport<UserDetails> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String string;
	private String mobileNumber;
	private String website;
	private String locale;
	

	public UserDetails(String firstName, String lastName, String email, String string, String mobileNumber, String website, String locale
			) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(string);
		setMobileNumber(mobileNumber);
		setWebsite(website);
		setLocale(locale);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return string;
	}

	public void setPhoneNumber(String string) {
		this.string = string;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	/** required by Hibernate **/
	UserDetails(){
		
	}

}