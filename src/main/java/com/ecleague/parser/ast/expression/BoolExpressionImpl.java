package com.ecleague.parser.ast.expression;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/13<br/>
 * Email: byp5303628@hotmail.com
 */
public class BoolExpressionImpl extends TypeExpressionImpl
      implements Expression {
   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }
}
