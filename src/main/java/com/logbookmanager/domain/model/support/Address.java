package com.logbookmanager.domain.model.support;

import com.logbookmanager.domain.model.Country;
import com.logbookmanager.domain.support.ValueObjectSupport;

/**
 * This class is used to represent an address. </p>
 * 
 * <p>
 * <a href="Address.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 * 
 */
public class Address extends ValueObjectSupport<Address> {
	private final static long serialVersionUID = 912839123l;

	private String nameOrNumber;

	private String street;

	private String city;

	private String province;

	private Country country;

	private String postCode;

	// required by Hibernate
	public Address() {
	}

	public Address(Country country) {
		this.country = country;
	}

	public Address(String nameOrNumber, String street, String city, String province, Country country, String postCode) {
		this.setNameOrNumber(nameOrNumber);
		this.setStreet(street);
		this.setCity(city);
		this.setCountry(country);
		this.setPostCode(postCode);
		this.setProvince(province);
	}

	/**
	 * Returns the city.
	 * 
	 * @return String
	 * 
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the province.
	 * 
	 * @return String
	 * 
	 * 
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Returns the country.
	 * 
	 * @return String
	 * 
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Returns the postCode.
	 * 
	 * @return String
	 * 
	 */
	public String getpostCode() {
		return getPostCode();
	}

	/**
	 * Sets the city.
	 * 
	 * @param city
	 *            The city to set
	 * 
	 * @t-spring.validator type="required"
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country
	 *            The country to set
	 * 
	 * @t-spring.validator type="required"
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Sets the postCode.
	 * 
	 * @param postCode
	 *            The postCode to set
	 * 
	 * @t-spring.validator type="required"
	 * @t-spring.validator type="mask" msgkey="errors.zip"
	 * @t-spring.validator-var name="mask" value="${zip}"
	 */
	public void setpostCode(String postCode) {
		this.setPostCode(postCode);
	}

	/**
	 * Sets the province.
	 * 
	 * @param province
	 *            The province to set
	 * 
	 * @t-spring.validator type="required"
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @Override public boolean equals(Object object) {
	 * 
	 *           if (!(object instanceof Address)) { return false; }
	 * 
	 *           Address rhs = (Address) object;
	 * 
	 *           return new EqualsBuilder().append(this.getPostCode(),
	 *           rhs.getPostCode()).append(this.getCountry(), rhs.getCountry())
	 *           .append(this.getNameOrNumber(),
	 *           rhs.getNameOrNumber()).append(this.getProvince(),
	 *           rhs.getProvince()) .append(this.getCity(),
	 *           rhs.getCity()).isEquals(); }
	 */

	/**
	 * @Override public int hashCode() { return new HashCodeBuilder(-426830461,
	 *           631494429).append(this.getPostCode()).append(this.getCountry())
	 *           .append(this.getNameOrNumber()).append(this.getProvince()).
	 *           append(this.getCity()).toHashCode(); }
	 */

	/**
	 * @Override public String toString() { return new ToStringBuilder(this,
	 *           ToStringStyle.MULTI_LINE_STYLE) .append("country",
	 *           (this.getCountry() != null) ? this.getCountry().toString() :
	 *           "[...]") .append("address", (this.getNameOrNumber() != null) ?
	 *           this.getNameOrNumber() : "[...]") .append("province",
	 *           this.getProvince()).append("postCode",
	 *           this.getPostCode()).append("city", this.getCity()) .toString();
	 *           }
	 */

	/*
	 * @Override public boolean sameValueAs(Address other) { // TODO
	 * Auto-generated method stub return false; }
	 */
	protected String getNameOrNumber() {
		return nameOrNumber;
	}

	protected void setNameOrNumber(String nameOrNumber) {
		this.nameOrNumber = nameOrNumber;
	}

	protected String getStreet() {
		return street;
	}

	protected void setStreet(String street) {
		this.street = street;
	}

	protected String getPostCode() {
		return postCode;
	}

	protected void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
