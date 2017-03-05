package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public class Expression implements SourceParser{
   private ArgType assignParam;

   private Operation operation;

   public ArgType getAssignParam() {
      return assignParam;
   }

   public void setAssignParam(ArgType assignParam) {
      this.assignParam = assignParam;
   }

   public Operation getOperation() {
      return operation;
   }

   public void setOperation(Operation operation) {
      this.operation = operation;
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
