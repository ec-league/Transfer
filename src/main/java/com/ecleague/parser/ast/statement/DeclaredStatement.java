package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.SourceParser;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class DeclaredStatement implements Statement, SourceParser {
   private ParamType paramType;
   private Operator operator;
   private Expression initExpression;

   public ParamType getParamType() {
      return paramType;
   }

   public void setParamType(ParamType paramType) {
      this.paramType = paramType;
   }

   public Operator getOperator() {
      return operator;
   }

   public void setOperator(Operator operator) {
      this.operator = operator;
   }

   public Expression getInitExpression() {
      return initExpression;
   }

   public void setInitExpression(Expression initExpression) {
      this.initExpression = initExpression;
   }

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;

      if (temp.startsWith(KeyWord.VAR)) {
         temp = temp.substring(3);
         temp = processVariable(temp);
         return processAssign(temp);
      } else if ((matcher =
            Pattern.compile(Regex.TYPE + " " + Regex.PARAM + "+").matcher(temp))
                  .find()) {
         matcher = Pattern.compile(Regex.TYPE).matcher(temp);
         paramType = new ParamType();
         if (matcher.find()) {
            paramType.setParamType(matcher.group());
         }

         temp = Util.trimTarget(temp, paramType.getParamType());
         temp = processVariable(temp);
         return processAssign(temp);
      } else {
         temp = processVariable(temp);
         return processAssign(temp);
      }
   }

   private String processVariable(String sourceCode) {
      sourceCode = StringUtils.trimToEmpty(sourceCode);
      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM + "+").matcher(sourceCode))
            .find()) {
         if (paramType == null)
            paramType = new ParamType();
         paramType.setParamName(matcher.group());

         sourceCode =
               sourceCode.substring(sourceCode.indexOf(paramType.getParamName())
                     + paramType.getParamName().length());

         return StringUtils.trimToEmpty(sourceCode);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }
   }

   private String processAssign(String sourceCode) {
      if (sourceCode.startsWith(Operators.ASSIGN)) {
         operator = new Operator();
         operator.setOperator("=");
         initExpression = new ExpressionImpl();

         sourceCode = sourceCode.substring(1);

         sourceCode = initExpression.parse(sourceCode);

         return sourceCode;
      } else {
         return sourceCode;
      }
   }
}
