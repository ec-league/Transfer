package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * BoolExpressionImpl Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class BoolExpressionImplTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: getExpressionType()
    * 
    */
   @Test
   public void testGetExpressionType() throws Exception {

      BoolExpressionImpl expression = new BoolExpressionImpl();

      String sourceCode = "true;";

      Assert.assertEquals(expression.parse(sourceCode), ";");

      Assert.assertEquals(expression.getType(), "boolean");

      Assert.assertEquals(expression.getName(), "true");

      sourceCode = "false && true;";

      Assert.assertEquals(expression.parse(sourceCode), "&& true;");

      Assert.assertEquals(expression.getType(), "boolean");

      Assert.assertEquals(expression.getName(), "false");

      sourceCode = "!abc";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(expression.getType(), "boolean");

      Assert.assertEquals(expression.getName(), "abc");
   }
}
