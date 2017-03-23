package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * ReturnStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ReturnStatementTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = "return 5;";

      ReturnStatement statement = new ReturnStatement();
      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.getExpression());
   }
}
