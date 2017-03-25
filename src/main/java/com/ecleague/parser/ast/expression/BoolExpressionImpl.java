package com.ecleague.parser.ast.expression;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.util.Util;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/13<br/>
 * Email: byp5303628@hotmail.com
 */
public class BoolExpressionImpl extends TypeExpressionImpl
      implements Expression {

   private boolean not;

   public BoolExpressionImpl() {
      getParamType().setParamType("boolean");
   }

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(KeyWord.TRUE)) {
         getParamType().setParamName("true");
         return Util.trimTarget(temp, getParamType().getParamName());
      } else if (temp.startsWith(KeyWord.FALSE)) {
         getParamType().setParamName("false");
         return Util.trimTarget(temp, getParamType().getParamName());
      } else if (temp.startsWith(Operators.NOT)) {
         not = true;
         temp = Util.trimTarget(temp, Operators.NOT);
         return parse(temp);
      } else {
         return super.parse(sourceCode);
      }
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
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }
}
