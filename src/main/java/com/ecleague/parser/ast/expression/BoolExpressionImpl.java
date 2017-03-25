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
   private Expression expression;

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
         setNot(true);
         temp = Util.trimTarget(temp, Operators.NOT);
         setExpression(new ExpressionImpl());
         return getExpression().parse(temp);
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
      StringBuilder sb = new StringBuilder();
      if (not)
         sb.append(Operators.NOT);
      if (getExpression() == null)
         return sb.append(getParamType().getParamName()).toString();
      else
         return sb.append(getExpression().toJavaCode()).toString();
   }

   @Override
   public String getExpressionType() {
      return KeyWord.BOOL;
   }

   public boolean isNot() {
      return not;
   }

   public void setNot(boolean not) {
      this.not = not;
   }

   public Expression getExpression() {
      return expression;
   }

   public void setExpression(Expression expression) {
      this.expression = expression;
   }
}
