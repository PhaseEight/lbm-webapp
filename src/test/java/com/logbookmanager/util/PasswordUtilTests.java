package com.logbookmanager.util;

import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilTests {

	@Test
	public void isComplexPassword() {
		String password = "!This1s@acompl_xP4ssword";
		Assert.assertTrue("It is not a complex password: " + password, PasswordUtil.isComplexPassword(password,8));
	}

	@Test
	public void isShortPassword() {
		String password = "password_too_short";
		Assert.assertFalse(PasswordUtil.isComplexPassword(password,password.length()+1));
	}
	
	@Test
	public void isNotComplexPassword() {
		String password = "th1sisnotacomplexpassword";
		Assert.assertFalse("It is a complex password: " + password, PasswordUtil.isComplexPassword(password,password.length()));
	}

	
	@Test
	public void allComplexPasswords() {
		for (String password : new String[] {
		        "!abC1efgh",
		        "abC1e@g)",
		        "abC1e_gh8",
		        }) {

		    Assert.assertTrue("String is not complex enough: " + password,PasswordUtil.isComplexPassword(password,password.length()));

		}		
	}

	@Test
	public void allNotComplexPasswords() {
		for (String password : new String[] {
		        "abcdefg",
		        "abcdefgh",
		        "abCdefgh",
		        "abc1efgh",
		        "1_g"
		        }) {

			Assert.assertFalse("String is complex enough: " + password,PasswordUtil.isComplexPassword(password,password.length()));

		}		
	}

	
}
