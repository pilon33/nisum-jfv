package com.nisum.test.security;
import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {

  /**
   * generates the value sha256 for the given password
   *
   */
  public static String encryptPassword(String password) {
    String sha256hex = DigestUtils.sha256Hex(password);
    return sha256hex;
  }

}
