package com.luqili.utils.pub.crypto;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加密机
 * @author luqili 2017年7月13日 下午11:52:36
 *
 */
public class Encryptors {
  private static final String CHARSET_NAME="UTF-8";
  /**
   * DES对称加密
   * 
   * @author 路其立
   * @version 2015-7-1上午09:31:22
   * @param src
   * @param key
   * @return
   */
  public static byte[] desEncrypt(byte[] src, String pwd) {
    try {
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec desKeySpec = new DESKeySpec(pwd.getBytes(CHARSET_NAME));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      IvParameterSpec iv = new IvParameterSpec(pwd.getBytes(CHARSET_NAME));
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
      return cipher.doFinal(src);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  /**
   * DES 将字符串进行加密
   * @param src
   * @param pwd
   * @return
   */
  public static String desEncrypt(String src, String pwd) {
    try {
      byte[] b_src =src.getBytes(CHARSET_NAME);
      byte[] r=desEncrypt(b_src, pwd);
      return Base64.getEncoder().encodeToString(r);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }
  

  /**
   * DES解密
   * 
   * @author 路其立
   * @version 2015-7-1上午09:34:34
   * @param v
   * @param key
   * @return
   */
  public static byte[] desDecrypt(byte[] v, String key) {
    try {
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET_NAME));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET_NAME));
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
      return cipher.doFinal(v);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 解密 将经过BASE64编码后的结果进行解密
   * 
   * @author 路其立
   * @version 2015-7-1上午09:36:07
   * @param v
   * @param key
   * @return
   */
  public static String desDecryptBase64(String v, String key) {
    try {
      byte[] b = Base64.getDecoder().decode(v);
      byte[] s = desDecrypt(b, key);
      return new String(s);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
