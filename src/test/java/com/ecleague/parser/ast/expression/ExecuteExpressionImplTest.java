package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

import com.ecleague.parser.ast.csharp.KeyWord;

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
      Assert.assertEquals(executeExpression.getFunction().getFunctionName(),
            "someFunction");

      sourceCode = "someFunction1().someFunction2()";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");
      Assert.assertEquals(executeExpression.getFunction().getFunctionName(),
            "someFunction1");
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getFunctionName(),
            "someFunction2");

      sourceCode = "someFunction1().someFunction2";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");
      Assert.assertEquals(executeExpression.getFunction().getFunctionName(),
            "someFunction1");
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getFunctionName(),
            "someFunction2");

      sourceCode = "someFunction1(A).someFunction2(A, B)";

      Assert.assertEquals(executeExpression.parse(sourceCode), "");
      Assert.assertEquals(executeExpression.getFunction().getFunctionName(),
            "someFunction1");
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getFunctionName(),
            "someFunction2");
      Assert.assertEquals(
            executeExpression.getFunction().getExpressions().size(), 1);
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getExpressions().size(),
            2);

      sourceCode = "someFunction1(A).someFunction2(A, out B)";
      Assert.assertEquals(executeExpression.parse(sourceCode), "");
      Assert.assertEquals(executeExpression.getFunction().getFunctionName(),
            "someFunction1");
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getFunctionName(),
            "someFunction2");
      Assert.assertEquals(
            executeExpression.getFunction().getExpressions().size(), 1);
      Assert.assertEquals(
            executeExpression.getNext().getFunction().getExpressions().size(),
            2);
      Assert.assertEquals(executeExpression.getNext().getFunction()
            .getExpressions().get(1).getOut(), KeyWord.OUT);
   }
}
