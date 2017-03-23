package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * ForStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ForStatementTest {

   /**
    * 
    * Method: preProcess(String sourceCode)
    * 
    */
   @Test
   public void testPreProcess() throws Exception {
      String sourceCode = "for(int a = 8;a < 1000;a = a + 1){ return 5;}";

      ForStatement statement = new ForStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.getBreakExpression());
      Assert.assertNotNull(statement.getDeclaredStatementEnd());
      Assert.assertNotNull(statement.getDeclaredStatementStart());

      Assert.assertNotNull(statement.getInnerStatements());
   }
}
