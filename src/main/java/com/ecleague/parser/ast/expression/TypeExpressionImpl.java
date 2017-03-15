package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class TypeExpressionImpl implements Expression {

   private String type;
   private String name;

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM).matcher(sourceCode)).find()){
         setName(matcher.group());
      }

      return Util.trimTarget(temp, getName());
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = StringUtils.trimToEmpty(type);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.OBJECT;
   }
}
