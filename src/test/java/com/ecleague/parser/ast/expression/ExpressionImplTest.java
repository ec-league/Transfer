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
      ExpressionImpl operation = new ExpressionImpl();

      String sourceCode = "5 + 6;";

      Assert.assertEquals(operation.parse(sourceCode), "");

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "5");
      operation = (ExpressionImpl) operation.getRight();

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "6");

      sourceCode = "a + b + 7 - 4;";

      Assert.assertEquals(operation.parse(sourceCode), "");

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "a");

      operation = (ExpressionImpl) operation.getRight();

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "b");

      operation = (ExpressionImpl) operation.getRight();

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "7");

      operation = (ExpressionImpl) operation.getRight();

      Assert.assertEquals(((TypeExpressionImpl) operation.getLeft()).getName(),
            "4");
   }


}
