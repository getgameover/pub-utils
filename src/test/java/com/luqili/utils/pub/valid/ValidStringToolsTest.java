package com.luqili.utils.pub.valid;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class ValidStringToolsTest {

    @Test
    public void validPattern() {
        String phone1 = "13132209379";
        String phone2 = "1313220937988";
        String phone3 = "23132209379";
        String phone4 = "17662531025";
        boolean result1 = ValidStringTools.validPhone(phone1);
        boolean result2 = ValidStringTools.validPhone(phone2);
        boolean result3 = ValidStringTools.validPhone(phone3);
        boolean result4 = ValidStringTools.validPhone(phone4);
        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(false, result2);
        AssertJUnit.assertEquals(false, result3);
        AssertJUnit.assertEquals(true, result4);
    }

    @Test
    public void validIdCard() {
        String idcard1 = "230833198103063179";
        String idcard2 = "230833198103063178";
        boolean result1 = ValidStringTools.validIdCard(idcard1);
        boolean result2 = ValidStringTools.validIdCard(idcard2);
        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(false, result2);
    }

    @Test
    public void validQQ() {
        String qq1 = "1234";
        String qq2 = "12345";
        String qq3 = "1234577889";
        boolean result1 = ValidStringTools.validQQ(qq1);
        boolean result2 = ValidStringTools.validQQ(qq2);
        boolean result3 = ValidStringTools.validQQ(qq3);
        AssertJUnit.assertEquals(false, result1);
        AssertJUnit.assertEquals(true, result2);
        AssertJUnit.assertEquals(true, result3);
    }

    @Test
    public void validEmail() {
        String m1 = "1234@qq.com";
        String m2 = "a@luqili.com";
        String m3 = "qx_-dd@126.com";
        String m4 = "1234577889_''_@163.com";
        boolean result1 = ValidStringTools.validEmail(m1);
        boolean result2 = ValidStringTools.validEmail(m2);
        boolean result3 = ValidStringTools.validEmail(m3);
        boolean result4 = ValidStringTools.validEmail(m4);
        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(true, result2);
        AssertJUnit.assertEquals(true, result3);
        AssertJUnit.assertEquals(false, result4);
    }

    @Test
    public void validChinese() {
        String m1 = "这是一段中文";
        String m2 = "这是一段中文。";
        String m3 = "这是繁体字龍";
        String m4 = "偏僻字节谶莿鈽綦";
        boolean result1 = ValidStringTools.validChinese(m1);
        boolean result2 = ValidStringTools.validChinese(m2);
        boolean result3 = ValidStringTools.validChinese(m3);
        boolean result4 = ValidStringTools.validChinese(m4);
        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(false, result2);
        AssertJUnit.assertEquals(true, result3);
        AssertJUnit.assertEquals(true, result4);
    }

    @Test
    public void validLength() {
        String m1 = "这是一段中文";
        boolean result1 = ValidStringTools.validLength(m1, 0, 6);
        boolean result2 = ValidStringTools.validLength(m1, 6, 7);
        boolean result3 = ValidStringTools.validLength(m1, 7, 8);
        boolean result4 = ValidStringTools.validLength(m1, 2, 5);
        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(true, result2);
        AssertJUnit.assertEquals(false, result3);
        AssertJUnit.assertEquals(false, result4);
    }

    @Test
    public void validIpv4() {
        String m1 = "255.255.255.255";
        String m2 = "1.2.3.255";
        String m3 = "255.256.255.255";
        String m4 = "255.235.20.2.2";
        boolean result1 = ValidStringTools.validIpv4(m1);
        boolean result2 = ValidStringTools.validIpv4(m2);
        boolean result3 = ValidStringTools.validIpv4(m3);
        boolean result4 = ValidStringTools.validIpv4(m4);

        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(true, result2);
        AssertJUnit.assertEquals(false, result3);
        AssertJUnit.assertEquals(false, result4);
    }

    @Test
    public void validIpv6() {
        String m1 = "0:0:0:0:0:0:0:1";
        String m2 = "FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:1";
        String m3 = "FFFG:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:1";
        String m4 = "FFFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:1";
        boolean result1 = ValidStringTools.validIpv6(m1);
        boolean result2 = ValidStringTools.validIpv6(m2);
        boolean result3 = ValidStringTools.validIpv6(m3);
        boolean result4 = ValidStringTools.validIpv6(m4);

        AssertJUnit.assertEquals(true, result1);
        AssertJUnit.assertEquals(true, result2);
        AssertJUnit.assertEquals(false, result3);
        AssertJUnit.assertEquals(false, result4);
    }

}
