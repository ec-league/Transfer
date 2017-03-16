package com.ecleague.parser.ast.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/13<br/>
 * Email: byp5303628@hotmail.com
 */
public class NumberExpressionImpl extends TypeExpressionImpl {

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.NUMBERS).matcher(sourceCode))
            .find()) {
         getParamType().setParamName(matcher.group());
      }

      return Util.trimTarget(temp, getParamType().getParamName());
   }
}
