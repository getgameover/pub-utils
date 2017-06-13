package com.luqili.utils.pub.valid;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.luqili.utils.pub.valid.ValidStringTools;

public class ValidStringToolsTest {

  @Test
  public void validPattern() {
	  String phone1 ="13132209379";
	  String phone2 ="1313220937988";
	  String phone3 ="23132209379";
	  String phone4 ="17662531025";
	  boolean result1=ValidStringTools.validPhone(phone1);
	  boolean result2=ValidStringTools.validPhone(phone2);
	  boolean result3=ValidStringTools.validPhone(phone3);
	  boolean result4=ValidStringTools.validPhone(phone4);
	  AssertJUnit.assertEquals(true, result1);
	  AssertJUnit.assertEquals(false, result2);
	  AssertJUnit.assertEquals(false, result3);
	  AssertJUnit.assertEquals(true, result4);
  }
  @Test
  public void validIdCard() {
	  String idcard1="230833198103063179";
	  String idcard2="230833198103063178";
	  boolean result1=ValidStringTools.validIdCard(idcard1);
	  boolean result2=ValidStringTools.validIdCard(idcard2);
	  AssertJUnit.assertEquals(true, result1);
	  AssertJUnit.assertEquals(false, result2);
  }

  @Test
  public void validQQ() {
	  String qq1="1234";
	  String qq2="12345";
	  String qq3="1234577889";
	  boolean result1=ValidStringTools.validQQ(qq1);
	  boolean result2=ValidStringTools.validQQ(qq2);
	  boolean result3=ValidStringTools.validQQ(qq3);
	  AssertJUnit.assertEquals(false, result1);
	  AssertJUnit.assertEquals(true, result2);
	  AssertJUnit.assertEquals(true, result3);
  }
  @Test
  public void validEmail() {
	  String m1="1234@qq.com";
	  String m2="a@luqili.com";
	  String m3="qx_-dd@126.com";
	  String m4="1234577889_''_@163.com";
	  boolean result1=ValidStringTools.validEmail(m1);
	  boolean result2=ValidStringTools.validEmail(m2);
	  boolean result3=ValidStringTools.validEmail(m3);
	  boolean result4=ValidStringTools.validEmail(m4);
	  AssertJUnit.assertEquals(true, result1);
	  AssertJUnit.assertEquals(true, result2);
	  AssertJUnit.assertEquals(true, result3);
	  AssertJUnit.assertEquals(false, result4);
  }
}
