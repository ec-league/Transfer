package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * DeclaredStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class DeclaredStatementTest {
   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParseVar() throws Exception {
      String sourceCode = "int a = 1 + 2;";

      DeclaredStatement statement = new DeclaredStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertEquals(statement.getParamType().getParamType(), "int");
      Assert.assertEquals(statement.getParamType().getParamName(), "a");
   }
}
