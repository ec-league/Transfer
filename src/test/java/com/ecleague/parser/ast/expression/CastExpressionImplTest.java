package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * CastExpressionImpl Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class CastExpressionImplTest {

   /**
    * 
    * Method: getExpressionType()
    * 
    */
   @Test
   public void testGetExpressionType() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = "(int) a + 5;";

      CastExpressionImpl expression = new CastExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "+ 5;");

      Assert.assertEquals(expression.getParamType().getParamName(), "a");
      Assert.assertEquals(expression.getParamType().getParamType(), "int");
   }

   @Test
   public void testToJavaCode() {
      String sourceCode = "(int) a";

      CastExpressionImpl expression = new CastExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(expression.toJavaCode(), "(int) a");
   }

}
