package com.luqili.utils.idcard;

import org.testng.annotations.Test;

public class IDCardUtilsTest {
	private String code="371521199004291410";
  @Test
  public void getBirthday() {
	  IDCardUtils.getBirthday(code);
  }

  @Test
  public void getGender() {
	  IDCardUtils.getGender(code);
  }


  @Test
  public void validIdCardCode() {
	  IDCardUtils.validIdCardCode(code);
  }

  @Test
  public void validIdCardCodeMessage() {
	  IDCardUtils.validIdCardCodeMessage(code);
  }
}
