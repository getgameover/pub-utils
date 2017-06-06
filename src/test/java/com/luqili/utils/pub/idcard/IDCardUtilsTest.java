package com.luqili.utils.pub.idcard;

import java.text.ParseException;
import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.luqili.utils.pub.date.DateUtils;
import com.luqili.utils.pub.idcard.IDCardUtils;

public class IDCardUtilsTest {
	private String idcard = "370902198211082117";
	private String creditcode = "811100000673062824";
	private String orgcode = "G34793367";

	@Test
	public void getIdCardBirthday() {
		Date date=IDCardUtils.getIdCardBirthday(idcard);
		try {
			AssertJUnit.assertEquals(DateUtils.parseDate("19821108", "yyyyMMdd"), date);
		} catch (ParseException e) {
			AssertJUnit.fail("日期异常");
		}
	}

	@Test
	public void getIdCardGender() {
		String g=IDCardUtils.getIdCardGender(idcard);
		AssertJUnit.assertEquals("男", g);
	}

	@Test
	public void validIdCardCode() {
		boolean result=IDCardUtils.validIdCardCode(idcard);
		AssertJUnit.assertEquals(true, result);
	}

	@Test
	public void validIdCardCodeMessage() {
		boolean result=IDCardUtils.validIdCardCodeMessage(idcard);
		AssertJUnit.assertEquals(true, result);
	}

	@Test
	public void validOrgCode() {
		boolean result=IDCardUtils.validOrgCode(orgcode);
		AssertJUnit.assertEquals(true, result);
	}

	@Test
	public void vliadCreditCode() {
		boolean result=IDCardUtils.vliadCreditCode(creditcode);
		AssertJUnit.assertEquals(true, result);
	}
}
