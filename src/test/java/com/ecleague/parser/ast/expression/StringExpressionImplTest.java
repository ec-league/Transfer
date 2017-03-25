package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * StringExpressionImpl Tester.
 *
 * @author EthanPark
 * @version 1.0
 * @since
 * 
 *        <pre>
 * 三月 25, 2017
 *        </pre>
 */
public class StringExpressionImplTest {

   /**
    * Method: parse(String sourceCode)
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = "\"abc\"";

      StringExpressionImpl expression = new StringExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(expression.getParamType().getParamType(), "string");
      Assert.assertEquals(expression.getValue(), "abc");

      sourceCode = "\"ab\\\"\"";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(expression.getParamType().getParamType(), "string");
      Assert.assertEquals(expression.getValue(), "ab\\\"");
   }
}
