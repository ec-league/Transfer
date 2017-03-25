package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * ExecuteExpressionImpl Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ExecuteExpressionImplTest {


   @Test
   public void testParse() {
      ExecuteExpressionImpl executeExpression = new ExecuteExpressionImpl();
      String sourceCode = "someFunction()";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");

      sourceCode = "someFunction1().someFunction2()";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");

      sourceCode = "someFunction1().someFunction2";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");

      sourceCode = "someFunction1(A).someFunction2(A, B)";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");

      sourceCode = "someFunction1(A).someFunction2(A, out B)";
      Assert.assertEquals(executeExpression.parse(sourceCode), "");
   }

   @Test
   public void testParse1() {
      ExecuteExpressionImpl executeExpression = new ExecuteExpressionImpl();
      String sourceCode = "CacheFactory.GetCache<IUpgradeProductCache>()";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");

      Assert.assertEquals(executeExpression.getNext().getTemplateName(),
            "IUpgradeProductCache");
   }
}
