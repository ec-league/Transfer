package com.ecleague.parser.ast.statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.java.JavaKeyWord;
import com.ecleague.parser.ast.util.Util;

/**
 * Created by coraline on 17/3/24.
 */
public class SwitchCaseStatement implements Statement {
   private Expression switchExpression;

   private List<CaseBlock> caseBlockList = new ArrayList<>();

   public Expression getExpression() {
      return switchExpression;
   }

   public void setExpression(Expression expression) {
      this.switchExpression = expression;
   }

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.SWITCH))
         throw new ParseSyntaxException(this, sourceCode);

      temp = Util.trimTarget(temp, KeyWord.SWITCH);
      temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

      switchExpression = new ExpressionImpl();
      temp = switchExpression.parse(temp);

      temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
      temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
      while (temp.startsWith(KeyWord.CASE)) {
         CaseBlock caseBlock = new CaseBlock();
         Expression expression = new ExpressionImpl();
         temp = Util.trimTarget(temp, KeyWord.CASE);
         temp = expression.parse(temp);
         caseBlock.setExpression(expression);
         temp = Util.trimTarget(temp, Operators.COLON);
         if (temp.startsWith(KeyWord.CASE)) {
            caseBlockList.add(caseBlock);
            continue;
         } else {
            List<Statement> statementList = new ArrayList<>();
            temp = StatementFactory.processInnerBlock(temp, statementList);
            caseBlock.setStatementList(statementList);
            caseBlockList.add(caseBlock);
         }
      }

      if (temp.startsWith(KeyWord.DEFAULT)) {
         CaseBlock caseBlock = new CaseBlock();

         temp = Util.trimTarget(temp, KeyWord.DEFAULT);
         temp = Util.trimTarget(temp, Operators.COLON);
         List<Statement> statementList = new ArrayList<>();
         temp = StatementFactory.processBlock(temp, statementList);
         caseBlock.setStatementList(statementList);
         caseBlockList.add(caseBlock);
      }

      return Util.trimTarget(temp, Operators.RIGHT_BRACE);

   }

   /**
    * Take the ast element to Java code.
    *
    * @return
    */
   @Override
   public String toJavaCode() {
      StringBuilder javaSwitch = new StringBuilder(JavaKeyWord.SWITCH);
      javaSwitch.append(Operators.LEFT_BRACKET);
      javaSwitch.append(getExpression().toJavaCode());
      javaSwitch.append(Operators.RIGHT_BRACKET);
      javaSwitch.append(Operators.LEFT_BRACE).append("\n");
      for (CaseBlock caseBlock : caseBlockList) {
         if (caseBlock.getExpression() == null) {
            javaSwitch.append(JavaKeyWord.DEFAULT);
            javaSwitch.append(Operators.COLON);
            javaSwitch.append("\n");
         } else {
            javaSwitch.append(JavaKeyWord.CASE);
            javaSwitch.append(" ");
            javaSwitch.append(caseBlock.getExpression().toJavaCode());
            javaSwitch.append(Operators.COLON);
            javaSwitch.append("\n");
         }
         if (caseBlock.getStatementList() != null
               && !caseBlock.getStatementList().isEmpty()) {
            for (Statement statement : caseBlock.getStatementList()) {
               javaSwitch.append(statement.toJavaCode());
               javaSwitch.append("\n");
            }

         }


      }
      javaSwitch.append(Operators.RIGHT_BRACE);
      System.out.println(javaSwitch);
      return javaSwitch.toString();
   }


   public static class CaseBlock {
      private Expression expression;
      private List<Statement> statementList;

      public Expression getExpression() {
         return expression;
      }

      public void setExpression(Expression expression) {
         this.expression = expression;
      }

      public List<Statement> getStatementList() {
         return statementList;
      }

      public void setStatementList(List<Statement> statementList) {
         this.statementList = statementList;
      }
   }
}
