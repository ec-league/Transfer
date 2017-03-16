package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.csharp.KeyWord;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public abstract class AbstractExpression implements Expression {
   protected String ref;
   protected String out;

   public String getRef() {
      return ref;
   }

   public void setRef() {
      this.ref = KeyWord.REF;
   }

   public String getOut() {
      return out;
   }

   public void setOut() {
      this.out = KeyWord.OUT;
   }
}
