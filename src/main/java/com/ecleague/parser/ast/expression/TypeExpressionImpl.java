package com.ecleague.parser.ast.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class TypeExpressionImpl extends AbstractExpression
      implements Expression {

   private ParamType paramType = new ParamType();

   private ExecuteExpressionImpl next;

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM).matcher(sourceCode)).find()) {
         paramType.setParamName(matcher.group());
         temp = Util.trimTarget(temp, paramType.getParamName());
      }

      return parseNext(temp);
   }

   /**
    * Take the ast element to Java code.
    *
    * @return
    */
   @Override
   public String toJavaCode() {
      return null;
   }

   protected String parseNext(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(".")) {
         temp = Util.trimTarget(temp, ".");
         next = new ExecuteExpressionImpl();
         return next.parse(temp);
      }

      return temp;
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.OBJECT;
   }

   public ParamType getParamType() {
      return paramType;
   }

   public void setParamType(ParamType paramType) {
      this.paramType = paramType;
   }

   public ExecuteExpressionImpl getNext() {
      return next;
   }

   public void setNext(ExecuteExpressionImpl next) {
      this.next = next;
   }
}
