package com.elasticsearch.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

  public static String preparePathBasedID(String path) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] partialHash = md.digest(path.getBytes());
      return String.format("%1$032X", new BigInteger(1, partialHash));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return path;
    }
  }
}
