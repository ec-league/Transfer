package com.ecleague.parser.ast.expression;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.Regex;
import com.ecleague.parser.ast.csharp.Operators;

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
      if (sourceCode.indexOf(Operators.LEFT_BRACKET) == 0) {

         String innerItem = sourceCode.substring(1,
               sourceCode.indexOf(Operators.RIGHT_BRACKET));

         if (innerItem.matches(Regex.TYPE)) {
            left = new TypeExpressionImpl();
            sourceCode = left.parse(sourceCode);
         }

         sourceCode = left.parse(innerItem);
      } else {
         sourceCode = StringUtils.trimToEmpty(sourceCode);
         left = new TypeExpressionImpl();
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
