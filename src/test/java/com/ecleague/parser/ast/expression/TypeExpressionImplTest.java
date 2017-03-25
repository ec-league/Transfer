package com.ecleague.parser.ast.expression;

import org.junit.Assert;
import org.junit.Test;

/**
 * TypeExpressionImpl Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class TypeExpressionImplTest {
   @Test
   public void testParseWithOriginParam() {
      String sourceCode = "someparam1 + someparam2";

      TypeExpressionImpl typeOperation = new TypeExpressionImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, "+ someparam2");
      Assert.assertEquals(typeOperation.getParamType().getParamName(),
            "someparam1");
   }
}
