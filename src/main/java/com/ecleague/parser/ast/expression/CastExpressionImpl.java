package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.function.Function;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class CastExpressionImpl extends AbstractExpression
      implements Expression {
   private ParamType paramType;

   private Function function;

   @Override
   public ExpressionType getExpressionType() {
      return null;
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
               throw new NotImplementedException();
            } else {
               return temp;
            }

         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }
      }
      throw new ParseSyntaxException(this, sourceCode);
   }

   public ParamType getParamType() {
      return paramType;
   }

   public void setParamType(ParamType paramType) {
      this.paramType = paramType;
   }

   public Function getFunction() {
      return function;
   }

   public void setFunction(Function function) {
      this.function = function;
   }
}
