package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.Operators;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/16<br/>
 * Email: byp5303628@hotmail.com
 */
public class StatementFactory {
   private static final String FOR_STATEMENT = "^for *\\(.*";
   private static final String FOREACH_STATEMENT = "^foreach *\\(.*";
   private static final String WHILE_STATEMENT = "^while *\\(.*";
   private static final String DECLARED_STATEMENT =
         "^[A-Za-z][A-Za-z0-9]* *[A-Za-z0-9].*";
   private static final String USING_STATEMENT = "^using *";
   private static final String RETURN_STATEMENT = "^return *.*";
   private static final String SWITCH_CASE_STATEMENT = "^switch *\\(.*";
   private static final String BREAK_STATEMENT = "^break!\\d";
   private static final String CONTINUE_STATEMENT = "^continue!\\d";

   public static Statement getStatement(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.matches(FOR_STATEMENT)) {
         return new ForStatement();
      } else if (temp.matches(RETURN_STATEMENT)) {
         return new ReturnStatement();
      } else if (temp.matches(BREAK_STATEMENT)) {
         return new BreakStatement();
      } else if (temp.matches(CONTINUE_STATEMENT)) {
         return new ContinueStatement();
      } else if (temp.matches(FOREACH_STATEMENT)) {
         return new ForEachStatement();
      } else if (temp.matches(WHILE_STATEMENT)) {
         return new WhileStatement();
      } else if (Pattern.compile(USING_STATEMENT).matcher(temp).find()) {
         return new UsingStatement();
      } else if (temp.matches(DECLARED_STATEMENT)) {
         return new DeclaredStatement();
      } else if (temp.matches(SWITCH_CASE_STATEMENT)) {
         return new SwitchCaseStatement();
      } else {
         return new ExecuteStatement();
      }
   }

   /**
    * Handle many lines of code. Until reach a return statement or reach a }
    * operator.
    *
    * @param sourceCode
    * @param statements
    * @return
    */
   public static String processBlock(String sourceCode,
                                     List<Statement> statements) {
      while (sourceCode.startsWith(Operators.RIGHT_BRACE)) {
         Statement statement = getStatement(sourceCode);

         sourceCode = statement.parse(sourceCode);

         statements.add(statement);

         if (statement instanceof ReturnStatement) {
            break;
         }
      }
      return sourceCode;
   }
}
