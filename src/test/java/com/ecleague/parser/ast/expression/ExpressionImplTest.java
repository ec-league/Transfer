package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * ExpressionImpl Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class ExpressionImplTest {
   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      ExpressionImpl expression = new ExpressionImpl();

      String sourceCode = "5 + 6;";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft()).getName(),
            "5");
      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft()).getName(),
            "6");

      sourceCode = "a + b + 7 - 4;";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft()).getName(),
            "a");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft()).getName(),
            "b");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((NumberExpressionImpl) expression.getLeft()).getName(),
            "7");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((NumberExpressionImpl) expression.getLeft()).getName(),
            "4");
   }
}
