package com.ecleague.parser.ast.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.exception.ParseSyntaxException;

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

   /**
    * Split the target sourceCode and turn into a list of strings.
    *
    * @param sourceCode,
    *           origin sourceCode
    * @param list,
    *           result list
    * @param sep,
    *           separator operator
    * @return
    */
   public static String processList(String sourceCode, List<String> list,
         String sep) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      while (true) {
         Matcher matcher;

         if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
            String s = matcher.group();
            list.add(s);
            temp = trimTarget(temp, s);
         } else {
            throw new ParseSyntaxException(Util.class, sourceCode);
         }

         if (temp.startsWith(sep)) {
            temp = trimTarget(temp, sep);
            continue;
         } else {
            break;
         }
      }

      return StringUtils.trimToEmpty(temp);
   }
}
