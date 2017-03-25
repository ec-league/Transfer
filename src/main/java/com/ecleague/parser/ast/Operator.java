package com.ecleague.parser.ast;

import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class Operator implements SourceParser {
   private String operator;

   private static final Set<String> CALCULATE_OPERATORS = new HashSet<>();

   static {
      CALCULATE_OPERATORS.add(Operators.PLUS);
      CALCULATE_OPERATORS.add(Operators.MINUS);
      CALCULATE_OPERATORS.add(Operators.MULTIPLY);
      CALCULATE_OPERATORS.add(Operators.DIVIDE);
      CALCULATE_OPERATORS.add(Operators.MOD);
      CALCULATE_OPERATORS.add(Operators.AND);
      CALCULATE_OPERATORS.add(Operators.OR);
      CALCULATE_OPERATORS.add(Operators.EQUAL);
      CALCULATE_OPERATORS.add(Operators.BIT_AND);
      CALCULATE_OPERATORS.add(Operators.BIT_OR);
      CALCULATE_OPERATORS.add(Operators.LT);
      CALCULATE_OPERATORS.add(Operators.GT);
      CALCULATE_OPERATORS.add(Operators.LT_EQ);
      CALCULATE_OPERATORS.add(Operators.GT_EQ);
      CALCULATE_OPERATORS.add(Operators.ASSIGN);
   }

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
      else {
         return "";
      }

      return Util.trimTarget(sourceCode, getOperator());
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
      case Operators.SEMICOLON:
         operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.BIT_AND:
         if (operatorStr.substring(0, 2).equals(Operators.AND))
            operator = Operators.AND;
         else
            operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.BIT_OR:
         if (operatorStr.substring(0, 2).equals(Operators.OR))
            operator = Operators.OR;
         else
            operator = Character.toString(operatorStr.charAt(0));
         break;
      case Operators.ASSIGN:
         if (operatorStr.length() > 1
               && operatorStr.substring(0, 2).equals(Operators.EQUAL))
            operator = Operators.EQUAL;
         else
            operator = Operators.ASSIGN;
         break;
      case Operators.GT:
         if (operatorStr.length() > 1
               && operatorStr.substring(0, 2).equals(Operators.GT_EQ))
            operator = Operators.GT_EQ;
         else
            operator = Operators.GT;
         break;
      case Operators.LT:
         if (operatorStr.length() > 1
               && operatorStr.substring(0, 2).equals(Operators.LT_EQ))
            operator = Operators.LT_EQ;
         else
            operator = Operators.LT;
         break;
      case Operators.RIGHT_BRACKET:
         operator = "";
         break;
      }
   }

   public boolean isCalculateOperator() {
      return CALCULATE_OPERATORS.contains(operator);
   }
}
