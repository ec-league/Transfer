package com.ecleague.parser.exception;

/**
 * Created by coraline on 17/3/24.
 */
public class PreFormatException extends RuntimeException {

   public PreFormatException(String msg, Exception ex) {
      super(msg, ex);
   }

   public PreFormatException(String msg) {
      super(msg);
   }
}
