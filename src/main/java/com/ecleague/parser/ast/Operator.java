package com.ecleague.parser.ast;

import com.ecleague.parser.ast.exception.ParseSyntaxException;
import org.apache.commons.lang.StringUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class Operator implements SourceParser {
   private String operator;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      sourceCode = StringUtils.trimToEmpty(sourceCode);

      if (StringUtils.isNotEmpty(sourceCode))
         setOperator(sourceCode);
      else
         throw new ParseSyntaxException(
               String.format("Parse operator failed : %s", sourceCode));

      return sourceCode.substring(operator.length());
   }

   public String getOperator() {
      return operator;
   }

   public void setOperator(String operatorStr) {
      switch (operatorStr.charAt(0)) {
      case OperatorCategory.PLUS:
      case OperatorCategory.MINUS:
      case OperatorCategory.MULTI:
      case OperatorCategory.DIVIDE:
         operator = Character.toString(operatorStr.charAt(0));
         break;
      case OperatorCategory.BIT_AND:
         if (operatorStr.charAt(1) == OperatorCategory.BIT_AND)
            setOperator("&&");
         operator = Character.toString(operatorStr.charAt(0));
         break;
      case OperatorCategory.BIT_OR:
         if (operatorStr.charAt(1) == OperatorCategory.BIT_OR)
            setOperator("||");
         operator = Character.toString(operatorStr.charAt(0));
         break;
      }
   }

   private static class OperatorCategory {
      private static final char PLUS = '+';
      private static final char MINUS = '-';
      private static final char MULTI = '*';
      private static final char DIVIDE = '/';
      private static final char BIT_AND = '&';
      private static final char BIT_OR = '|';
   }
}
