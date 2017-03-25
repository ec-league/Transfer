package com.ecleague.parser.ast.expression;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class EmptyExpression extends AbstractExpression {
   @Override
   public String getExpressionType() {
      return null;
   }

   @Override
   public String parse(String sourceCode) {
      return "";
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
}
