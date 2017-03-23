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
public class ForStatement extends BlockStatement implements Statement {
   private DeclaredStatement declaredStatementStart;
   private Expression breakExpression;
   private DeclaredStatement declaredStatementEnd;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   @Override
   protected String preProcess(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.FOR))
         throw new ParseSyntaxException(this, sourceCode);

      temp = Util.trimTarget(temp, KeyWord.FOR);

      temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

      if (!temp.startsWith(Operators.SEMICOLON)) {
         declaredStatementStart = new DeclaredStatement();
         temp = declaredStatementStart.parse(temp);
      } else {
         temp = Util.trimTarget(temp, Operators.SEMICOLON);
      }

      if (!temp.startsWith(Operators.SEMICOLON)) {
         breakExpression = new ExpressionImpl();
         temp = breakExpression.parse(temp);
      } else {
         temp = Util.trimTarget(temp, Operators.SEMICOLON);
      }

      if (!temp.startsWith(Operators.RIGHT_BRACKET)) {
         declaredStatementEnd = new DeclaredStatement();
         temp = declaredStatementEnd.parse(temp);
      }

      return Util.trimTarget(temp, Operators.RIGHT_BRACKET);
   }

   public DeclaredStatement getDeclaredStatementStart() {
      return declaredStatementStart;
   }

   public void setDeclaredStatementStart(
         DeclaredStatement declaredStatementStart) {
      this.declaredStatementStart = declaredStatementStart;
   }

   public DeclaredStatement getDeclaredStatementEnd() {
      return declaredStatementEnd;
   }

   public void setDeclaredStatementEnd(DeclaredStatement declaredStatementEnd) {
      this.declaredStatementEnd = declaredStatementEnd;
   }

   public Expression getBreakExpression() {
      return breakExpression;
   }

   public void setBreakExpression(Expression breakExpression) {
      this.breakExpression = breakExpression;
   }
}
