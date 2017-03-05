package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class DeclaredStatement extends Statement implements SourceParser{
   private String declaredType;
   private String initValue;

   public String getDeclaredType() {
      return declaredType;
   }

   public void setDeclaredType(String declaredType) {
      this.declaredType = declaredType;
   }

   public String getInitValue() {
      return initValue;
   }

   public void setInitValue(String initValue) {
      this.initValue = initValue;
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
