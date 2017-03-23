package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public abstract class BlockStatement implements Statement {

   private List<Statement> innerStatements;

   protected abstract String preProcess(String sourceCode);

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      temp = preProcess(sourceCode);

      return postProcess(temp);
   }

   protected String postProcess(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      innerStatements = new ArrayList<>();
      if (temp.startsWith(Operators.LEFT_BRACE)) {
         temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
         temp = StatementFactory.processBlock(temp, innerStatements);

         return Util.trimTarget(temp, Operators.RIGHT_BRACE);
      } else {

         Statement statement = StatementFactory.getStatement(temp);
         temp = statement.parse(temp);
         innerStatements.add(statement);
         return temp;
      }
   }

   public List<Statement> getInnerStatements() {
      return innerStatements;
   }

   public void setInnerStatements(List<Statement> innerStatements) {
      this.innerStatements = innerStatements;
   }
}
