package com.ecleague.parser.ast.statement;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.SourceParser;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class ExecuteStatement implements Statement, SourceParser {
   private Expression expression;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
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
}
