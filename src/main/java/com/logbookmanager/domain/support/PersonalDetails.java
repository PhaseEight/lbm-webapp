package com.logbookmanager.domain.support;

import com.logbookmanager.domain.model.support.Address;

public class PersonalDetails extends ValueObjectSupport<PersonalDetails> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address address;

	/** this is required by Hibernate **/
	PersonalDetails (){
		
	}

	public PersonalDetails(String firstName, String lastName, Address address,
			String email, String phoneNumber, String website
			) {
		setAddress(address);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}