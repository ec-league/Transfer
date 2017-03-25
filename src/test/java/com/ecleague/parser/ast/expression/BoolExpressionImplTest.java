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

      Assert.assertEquals(expression.getParamType().getParamType(), "boolean");

      Assert.assertEquals(expression.getParamType().getParamName(), "true");

      sourceCode = "false && true;";

      Assert.assertEquals(expression.parse(sourceCode), "&& true;");

      Assert.assertEquals(expression.getParamType().getParamType(), "boolean");

      Assert.assertEquals(expression.getParamType().getParamName(), "false");

      sourceCode = "!abc";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(expression.getParamType().getParamType(), "boolean");

      Assert.assertNotNull(expression.getExpression());
   }
}
