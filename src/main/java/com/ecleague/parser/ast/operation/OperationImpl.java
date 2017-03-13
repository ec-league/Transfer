package com.ecleague.parser.ast.operation;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.Regex;
import com.ecleague.parser.ast.csharp.Operators;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class OperationImpl implements Operation {
   private Operation left;
   private Operator operator;
   private Operation right;

   public Operation getLeft() {
      return left;
   }

   public void setLeft(Operation left) {
      this.left = left;
   }

   public Operator getOperator() {
      return operator;
   }

   public void setOperator(Operator operator) {
      this.operator = operator;
   }

   public Operation getRight() {
      return right;
   }

   public void setRight(Operation right) {
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
            left = new TypeOperationImpl();
            sourceCode = left.parse(sourceCode);
         }

         sourceCode = left.parse(innerItem);
      } else {
         sourceCode = StringUtils.trimToEmpty(sourceCode);
         left = new TypeOperationImpl();
         sourceCode = left.parse(sourceCode);
      }

      operator = new Operator();
      sourceCode = operator.parse(sourceCode);

      if (operator.isSemicolon()) {
         right = null;
         return "";
      }

      right = new OperationImpl();
      return right.parse(sourceCode);
   }
}
