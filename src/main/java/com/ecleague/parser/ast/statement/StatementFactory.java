package com.ecleague.parser.ast.statement;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.exception.ParseSyntaxException;

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

   public static Statement getStatement(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.matches(FOR_STATEMENT)) {
         return new ForStatement();
      } else if (temp.matches(FOREACH_STATEMENT)) {
         return new ForEachStatement();
      } else if (temp.matches(WHILE_STATEMENT)) {
         return new WhileStatement();
      } else if (Pattern.compile(USING_STATEMENT).matcher(temp).find()) {
         return new UsingStatement();
      } else if (temp.matches(DECLARED_STATEMENT)) {
         return new DeclaredStatement();
      } else {
         return new ExecuteStatement();
      }
   }
}
