package com.luqili.utils.pub.crypto;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class EncryptorsTest {

  @Test
  public void aesCrypt() {
    String src = "123456789012345612345678901234561234567890123456";
    String pwd = "1234567890123456";
    String enSrc = Encryptors.aesEncrypt(src, pwd);  
    String deSrc = Encryptors.aesDecrypt(enSrc, pwd);
    assertEquals(src, deSrc);
  }

  @Test
  public void hmacSHADecrypt() {
    String src = "123456789012345612345678901234561234567890123456";
    String pwd = "1234567890123456";
    String r1=Encryptors.hmacSHA1Decrypt(src, pwd);
    String r2=Encryptors.hmacSHA256Decrypt(src, pwd);
    assertNotNull(r1);
    assertNotNull(r2);
  }
}
