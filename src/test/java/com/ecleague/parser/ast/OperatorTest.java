package com.ecleague.parser.ast;

import org.junit.Assert;
import org.junit.Test;

/**
 * Operator Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class OperatorTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = " + ";

      Operator operator = new Operator();
      operator.parse(sourceCode);

      Assert.assertEquals(operator.getOperator(), "+");

      sourceCode = " + 10";

      Assert.assertEquals(operator.parse(sourceCode), " 10");
   }
}
