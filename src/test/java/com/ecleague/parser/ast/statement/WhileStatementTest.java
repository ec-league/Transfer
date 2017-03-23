package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * WhileStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class WhileStatementTest {

   /**
    * 
    * Method: preProcess(String sourceCode)
    * 
    */
   @Test
   public void testPreProcess() throws Exception {

      String sourceCode = "while(true){ return ;}";

      WhileStatement statement = new WhileStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.getExpression());
   }
}
