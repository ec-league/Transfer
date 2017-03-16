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

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "5");
      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "6");

      sourceCode = "a + b + 7 - 4;";

      Assert.assertEquals(expression.parse(sourceCode), "");

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "a");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((TypeExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "b");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((NumberExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "7");

      expression = (ExpressionImpl) expression.getRight();

      Assert.assertEquals(((NumberExpressionImpl) expression.getLeft())
            .getParamType().getParamName(), "4");
   }

   @Test
   public void testParseComplex1() {
      String sourceCode =
            "!product.MatchSeatGrade(entity.DepartMainSegment.SeatGrade)";

      Expression expression = new ExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "");
   }

   @Test
   public void testParseComplex2(){
      String sourceCode = "AgentCache.IsOwnAgency(agentId);";

      Expression expression = new ExpressionImpl();

      Assert.assertEquals(expression.parse(sourceCode), "");
   }
}
