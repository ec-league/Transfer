package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/16<br/>
 * Email: byp5303628@hotmail.com
 */
public class WhileStatement extends BlockStatement implements Statement {
   private Expression expression;

   @Override
   protected String preProcess(String sourceCode) {

      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.WHILE))
         throw new ParseSyntaxException(this, sourceCode);

      temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

      expression = new ExpressionImpl();
      temp = expression.parse(temp);

      return Util.trimTarget(temp, Operators.RIGHT_BRACKET);
   }

   public Expression getExpression() {
      return expression;
   }

   public void setExpression(Expression expression) {
      this.expression = expression;
   }
}
