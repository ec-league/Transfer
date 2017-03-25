package com.ecleague.parser.ast.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class CastExpressionImpl extends AbstractExpression
      implements Expression {
   private ParamType paramType;

   private ExecuteExpressionImpl next;

   public ExecuteExpressionImpl getNext() {
      return next;
   }

   public void setNext(ExecuteExpressionImpl next) {
      this.next = next;
   }

   @Override
   public String getExpressionType() {
      if (getNext() == null)
         return getParamType().getParamType();

      return getNext().getExpressionType();
   }

   @Override
   public String parse(String sourceCode) {
      String temp = Util.trimTarget(sourceCode, Operators.LEFT_BRACKET);

      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
         paramType = new ParamType();
         paramType.setParamType(matcher.group());

         temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);

         if ((matcher = Pattern.compile(Regex.VARIABLE).matcher(temp)).find()) {
            paramType.setParamName(matcher.group());
            temp = Util.trimTarget(temp, paramType.getParamName());
            temp = StringUtils.trimToEmpty(temp);

            if (temp.startsWith(".")) {
               // Force cast support function calls.
               temp = Util.trimTarget(temp, ".");
               setNext(new ExecuteExpressionImpl());
               return getNext().parse(temp);
            } else {
               return temp;
            }

         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }
      }
      throw new ParseSyntaxException(this, sourceCode);
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

   public ParamType getParamType() {
      return paramType;
   }

   public void setParamType(ParamType paramType) {
      this.paramType = paramType;
   }
}
