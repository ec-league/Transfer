package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * ExpressionFactory Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ExpressionFactoryTest {
   /**
    * 
    * Method: getExpression(String sourceCode)
    * 
    */
   @Test
   public void testGetExpression() throws Exception {

      String sourceCode = "abc";

      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof TypeExpressionImpl);

      sourceCode = "1 + 2";
      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof NumberExpressionImpl);

      sourceCode = "new Abc()";

      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof NewExpressionImpl);

      sourceCode = "Abc()";

      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof ExecuteExpressionImpl);

      sourceCode = "true;";
      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof BoolExpressionImpl);

      sourceCode = "(int) a";
      Assert.assertTrue(ExpressionFactory
            .getExpression(sourceCode) instanceof ExpressionImpl);
   }
}
