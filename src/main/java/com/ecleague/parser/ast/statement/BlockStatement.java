package com.ecleague.parser.ast.statement;

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
      temp = preProcess(temp);
      return postProcess(temp);
   }

   protected String postProcess(String sourceCode) {
      setInnerStatements(new ArrayList<Statement>());
      return StatementFactory.processInnerBlock(sourceCode, getInnerStatements());
   }

   public List<Statement> getInnerStatements() {
      return innerStatements;
   }

   public void setInnerStatements(List<Statement> innerStatements) {
      this.innerStatements = innerStatements;
   }
}
