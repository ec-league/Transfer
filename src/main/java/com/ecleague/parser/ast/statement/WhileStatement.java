package com.ecleague.parser.ast.statement;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.java.JavaKeyWord;
import com.ecleague.parser.ast.util.Util;

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

      temp = Util.trimTarget(temp, KeyWord.WHILE);
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

   /**
    * Take the ast element to Java code.
    *
    * @return
    */
   @Override
   public String toJavaCode() {

      StringBuilder javaWhile = new StringBuilder(JavaKeyWord.WHILE);
      javaWhile.append(Operators.LEFT_BRACKET);
      javaWhile.append(getExpression().toJavaCode());
      javaWhile.append(Operators.RIGHT_BRACKET);
      javaWhile.append(Operators.LEFT_BRACE).append("\n");
      if (getInnerStatements() != null && !getInnerStatements().isEmpty()) {
         for (Statement statement : getInnerStatements()) {
            javaWhile.append(statement.toJavaCode());
            javaWhile.append("\n");
         }

      }
      javaWhile.append(Operators.RIGHT_BRACE);
      System.out.println(javaWhile);

      return javaWhile.toString();
   }
}
