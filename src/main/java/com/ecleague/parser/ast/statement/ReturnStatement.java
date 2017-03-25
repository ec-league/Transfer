package com.ecleague.parser.ast.statement;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class ReturnStatement implements Statement {
   private Expression expression;

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.RETURN)) {
         throw new ParseSyntaxException(this, sourceCode);
      }

      temp = Util.trimTarget(temp, KeyWord.RETURN);

      if (temp.startsWith(Operators.SEMICOLON))
         return Util.trimTarget(temp, Operators.SEMICOLON);

      expression = new ExpressionImpl();
      return expression.parse(temp);
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

   public Expression getExpression() {
      return expression;
   }

   public void setExpression(Expression expression) {
      this.expression = expression;
   }
}
