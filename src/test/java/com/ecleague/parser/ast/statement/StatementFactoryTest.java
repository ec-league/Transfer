package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * StatementFactory Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class StatementFactoryTest {

   /**
    * 
    * Method: getStatement(String sourceCode)
    * 
    */
   @Test
   public void testGetStatement() throws Exception {
      String sourceCode = " while (true) {}";

      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof WhileStatement);

      sourceCode = " foreach(var x in list){}";

      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof ForEachStatement);

      sourceCode = "for(int i = 0;i< 7;i++)";

      Assert.assertTrue(
            StatementFactory.getStatement(sourceCode) instanceof ForStatement);

      sourceCode = "int a = 9;";

      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof DeclaredStatement);

      sourceCode = "using DomainModel=Ctrip.IntlFlight.DomainModel;";
      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof UsingStatement);

      sourceCode = "x = 5 + 6;";
      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof ExecuteStatement);

      sourceCode = "return 1;";
      Assert.assertTrue(StatementFactory
            .getStatement(sourceCode) instanceof ReturnStatement);

   }
}
