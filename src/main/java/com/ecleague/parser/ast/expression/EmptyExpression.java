package com.ecleague.parser.ast.expression;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class EmptyExpression extends AbstractExpression {
   @Override
   public ExpressionType getExpressionType() {
      return null;
   }

   @Override
   public String parse(String sourceCode) {
      return "";
   }
}
