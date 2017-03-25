package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class ExpressionImpl extends AbstractExpression implements Expression {
   private Expression left;
   private Operator operator;
   private Expression right;

   public Expression getLeft() {
      return left;
   }

   public void setLeft(Expression left) {
      this.left = left;
   }

   public Operator getOperator() {
      return operator;
   }

   public void setOperator(Operator operator) {
      this.operator = operator;
   }

   public Expression getRight() {
      return right;
   }

   public void setRight(Expression right) {
      this.right = right;
   }

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {

      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(Operators.LEFT_BRACKET)) {
         temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);
         left = new ExpressionImpl();
         temp = left.parse(temp);
         temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
      } else {
         left = ExpressionFactory.getExpression(temp);
         temp = left.parse(temp);
      }

      operator = new Operator();

      temp = operator.parse(temp);

      if (!operator.isCalculateOperator()) {
         right = null;
         return temp;
      }

      right = new ExpressionImpl();
      return right.parse(temp);
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

   @Override
   public String getExpressionType() {
      return getLeft().getExpressionType();
   }
}
