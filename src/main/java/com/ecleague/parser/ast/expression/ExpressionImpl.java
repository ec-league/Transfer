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
public class ExpressionImpl implements Expression {
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
      sourceCode = StringUtils.trimToEmpty(sourceCode);
      if (sourceCode.indexOf(Operators.LEFT_BRACKET) == 0) {
         String innerItem = Util.trimTarget(sourceCode, Operators.LEFT_BRACKET);
         left = ExpressionFactory.getExpression(innerItem);
         innerItem = left.parse(innerItem);
         sourceCode = Util.trimTarget(innerItem, Operators.RIGHT_BRACKET);
      } else {
         left = ExpressionFactory.getExpression(sourceCode);
         sourceCode = left.parse(sourceCode);
      }

      operator = new Operator();

      sourceCode = operator.parse(sourceCode);

      if (operator.isSemicolon()) {
         right = null;
         return "";
      }

      right = new ExpressionImpl();
      return right.parse(sourceCode);
   }

   @Override
   public ExpressionType getExpressionType() {
      return getLeft().getExpressionType();
   }
}
