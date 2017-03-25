package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yun.li on 2017/3/25.
 */
public class IfStatement implements Statement {

   private Expression ifExpression;
   private List<Statement> statements;
   private List<IfStatement> elseIfBlocks;

   public List<Statement> getStatements() {
      return statements;
   }

   public void setStatements(List<Statement> statements) {
      this.statements = statements;
   }

   public List<IfStatement> getElseIfBlocks() {
      return elseIfBlocks;
   }

   public void setElseIfBlocks(List<IfStatement> elseIfBlocks) {
      this.elseIfBlocks = elseIfBlocks;
   }

   public Expression getIfExpression() {
      return ifExpression;
   }

   public void setIfExpression(Expression ifExpression) {
      this.ifExpression = ifExpression;
   }

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.IF))
         throw new ParseSyntaxException(this, sourceCode);

      temp = Util.trimTarget(temp, KeyWord.IF);
      temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

      ifExpression = new ExpressionImpl();
      temp = ifExpression.parse(temp);
      temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
      setStatements(new ArrayList<Statement>());
      temp = StatementFactory.processInnerBlock(temp, getStatements());
      String elseIfTemp = StringUtils.trimToEmpty(temp);
      //// TODO: 2017/3/25 startWith判断的话有些问题
      while (elseIfTemp.startsWith(KeyWord.ELSE)) {
         elseIfTemp = Util.trimTarget(elseIfTemp, KeyWord.ELSE);
         if (!elseIfTemp.startsWith(KeyWord.IF)) {
            break;
         }
         elseIfTemp = Util.trimTarget(elseIfTemp, KeyWord.IF);

         IfStatement elseIfstatement = new IfStatement();
         Expression elseIfExpression = new ExpressionImpl();
         elseIfTemp = Util.trimTarget(elseIfTemp, Operators.LEFT_BRACKET);
         elseIfTemp = elseIfExpression.parse(elseIfTemp);
         elseIfTemp = Util.trimTarget(elseIfTemp, Operators.RIGHT_BRACKET);
         elseIfstatement.setIfExpression(elseIfExpression);

         elseIfstatement.setStatements(new ArrayList<Statement>());
         elseIfTemp = StatementFactory.processInnerBlock(elseIfTemp,
               elseIfstatement.getStatements());
         temp = elseIfTemp;
      }

      if (temp.startsWith(KeyWord.ELSE)) {
         temp = Util.trimTarget(temp, KeyWord.ELSE);
         IfStatement ifStatement = new IfStatement();
         ifStatement.setStatements(new ArrayList<Statement>());
         temp = StatementFactory.processInnerBlock(temp,
               ifStatement.getStatements());
      }
      return temp;
   }
}
