package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class ExecuteStatement extends Statement implements SourceParser{
   private Expression expression;

   public Expression getExpression() {
      return expression;
   }

   public void setExpression(Expression expression) {
      this.expression = expression;
   }

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public void parse(String sourceCode) {

   }
}
