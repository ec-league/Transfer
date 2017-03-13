package com.ecleague.parser.ast;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;

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
         throw new ParseSyntaxException(this, sourceCode);

      return sourceCode.substring(operator.length());
   }

   public String getOperator() {
      return operator;
   }

   public void setOperator(String operatorStr) {
      switch (operatorStr.substring(0, 1)) {
      case Operators.PLUS:
      case Operators.MINUS:
      case Operators.MULTIPLY:
      case Operators.DIVIDE:
      case Operators.MOD:
         operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.BIT_AND:
         if (operatorStr.substring(0, 2).equals(Operators.AND))
            setOperator(Operators.AND);
         else
            operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.BIT_OR:
         if (operatorStr.substring(0, 2).equals(Operators.OR))
            setOperator(Operators.OR);
         else
            operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.ASSIGN:
         if (operatorStr.substring(0, 2).equals(Operators.EQUAL))
            setOperator(Operators.EQUAL);
         break;
      }
   }
}
