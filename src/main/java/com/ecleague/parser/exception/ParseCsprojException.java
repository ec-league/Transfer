package com.ecleague.parser.exception;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public class ParseCsprojException extends RuntimeException {

   public ParseCsprojException(String msg, Exception ex) {
      super(msg, ex);
   }

   public ParseCsprojException(String msg) {
      super(msg);
   }
}
