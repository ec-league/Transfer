package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * NewExpressionImpl Tester.
 * 
 * @author EthanPark
 * @since
 * 
 * @version 1.0
 */
public class NewExpressionImplTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = "new List<string>()";
      NewExpressionImpl expression = new NewExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "");


   }
}
