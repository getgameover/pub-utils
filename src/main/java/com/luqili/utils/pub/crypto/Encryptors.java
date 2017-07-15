package com.luqili.utils.pub.crypto;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密机
 * 
 * @author luqili 2017年7月13日 下午11:52:36
 *
 */
public class Encryptors {
  private static final String CHARSET_NAME = "UTF-8";

  /**
   * 对字符串进行加密
   * 
   * @param src
   * @param pwd
   * @return
   */
  public static String aesEncrypt(String src, String pwd) {
    try {
      byte[] desKey = pwd.getBytes(CHARSET_NAME);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(desKey, "AES");
      IvParameterSpec iv = new IvParameterSpec(desKey, 0, 16);
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
      byte[] input = src.getBytes(CHARSET_NAME);
      byte[] result = cipher.doFinal(input);
      return Base64.getEncoder().encodeToString(result);
    } catch (Exception e) {
      throw new RuntimeException("AES encrypt is error !", e);
    }
  }

  /**
   * AES算法解密
   * <li>加密结果以Base64编码
   * <li>pwd为16位字符串
   * 
   * @param src
   * @param pwd
   * @return
   */
  public static String aesDecrypt(String src, String pwd) {
    try {
      byte[] desKey = pwd.getBytes(CHARSET_NAME);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(desKey, "AES");
      IvParameterSpec iv = new IvParameterSpec(desKey, 0, 16);
      cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
      byte[] input = Base64.getDecoder().decode(src);
      byte[] result = cipher.doFinal(input);
      return new String(result, CHARSET_NAME);
    } catch (Exception e) {
      throw new RuntimeException("AES decrypt is error !", e);
    }
  }
  /**
   * HmacSHA1 加密
   * @param src
   * @param pwd
   * @return
   */
  public static String hmacSHA1Decrypt(String src,String pwd) {
    try {
      byte[] desKey = pwd.getBytes(CHARSET_NAME);
      Mac mac=Mac.getInstance("HmacSHA1");
      SecretKeySpec keySpec = new SecretKeySpec(desKey, "MAC");
      mac.init(keySpec);
      byte[] result = mac.doFinal(src.getBytes(CHARSET_NAME));
      return Base64.getEncoder().encodeToString(result);
    } catch (Exception e) {
      throw new RuntimeException("AES decrypt is error !", e);
    }
  }
  /**
   * HmacSHA256 加密
   * @param src
   * @param pwd
   * @return
   */
  public static String hmacSHA256Decrypt(String src,String pwd) {
    try {
      byte[] desKey = pwd.getBytes(CHARSET_NAME);
      Mac mac=Mac.getInstance("HmacSHA256");
      SecretKeySpec keySpec = new SecretKeySpec(desKey, "MAC");
      mac.init(keySpec);
      byte[] result = mac.doFinal(src.getBytes(CHARSET_NAME));
      return Base64.getEncoder().encodeToString(result);
    } catch (Exception e) {
      throw new RuntimeException("AES decrypt is error !", e);
    }
  }

  
}
