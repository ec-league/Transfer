package com.ecleague.parser.ast.util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class Util {
   /**
    * Trim target string.
    * 
    * @param string
    * @param target
    * @return
    */
   public static String trimTarget(String string, String target) {
      return string.substring(string.indexOf(target) + target.length());
   }
}
