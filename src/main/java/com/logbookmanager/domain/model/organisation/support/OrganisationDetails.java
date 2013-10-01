package com.logbookmanager.domain.model.organisation.support;

import com.logbookmanager.domain.model.support.Address;
import com.logbookmanager.domain.support.EmailAddress;
import com.logbookmanager.domain.support.PhoneNumber;
import com.logbookmanager.domain.support.ValueObjectSupport;
import com.logbookmanager.domain.support.WebSite;

public class OrganisationDetails extends ValueObjectSupport<OrganisationDetails> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Address address;
	private EmailAddress emailAddress;
	private PhoneNumber landline;
	private PhoneNumber fax;

	/** this is required by Hibernate **/
	OrganisationDetails (){
		
	}

	public OrganisationDetails(Address address, EmailAddress email, WebSite website ) {
		this(address, email, null, null, website );
	}
	
	public OrganisationDetails(Address address, EmailAddress email, PhoneNumber phoneNumber, PhoneNumber fax, WebSite website ) {
		setAddress(address);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public PhoneNumber getLandline() {
		return landline;
	}

	public void setLandline(PhoneNumber landline) {
		this.landline = landline;
	}

	public PhoneNumber getFax() {
		return fax;
	}

	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}

	
}