package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/13<br/>
 * Email: byp5303628@hotmail.com
 */
public class BoolExpressionImpl extends TypeExpressionImpl
      implements Expression {

   private boolean not;

   public BoolExpressionImpl() {
      setType("boolean");
   }

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(KeyWord.TRUE)) {
         setName("true");
         return Util.trimTarget(temp, getName());
      } else if (temp.startsWith(KeyWord.FALSE)) {
         setName("false");
         return Util.trimTarget(temp, getName());
      } else if (temp.startsWith(Operators.NOT)) {
         not = true;
         temp = Util.trimTarget(temp, Operators.NOT);
         return parse(temp);
      } else {
         return super.parse(sourceCode);
      }
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }
}
