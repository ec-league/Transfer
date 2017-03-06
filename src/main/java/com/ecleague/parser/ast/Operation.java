package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class Operation implements SourceParser {
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
      if (sourceCode.indexOf('(') == 0) {
         left = new Operation();

         String leftOperation =
               sourceCode.substring(1, sourceCode.indexOf(')'));

         sourceCode = left.parse(leftOperation);
      }

      operator = new Operator();
      sourceCode = operator.parse(sourceCode);

      right = new Operation();
      return right.parse(sourceCode);
   }
}
