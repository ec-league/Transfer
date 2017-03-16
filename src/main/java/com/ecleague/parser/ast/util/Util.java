package com.ecleague.parser.ast.util;

import org.apache.commons.lang.StringUtils;

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
      if (target == null)
         return string;
      if (string.indexOf(target) == -1)
         return StringUtils.trimToEmpty(string);
      string = string.substring(string.indexOf(target) + target.length());
      return StringUtils.trimToEmpty(string);
   }
}
