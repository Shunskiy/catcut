package org.pk.catcut.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

  public static String generateId(String url) {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      byte[] hash = digest.digest(url.getBytes());
      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
        hexString.append(String.format("%02x", b));
      }

      return hexString.substring(0, 8);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
