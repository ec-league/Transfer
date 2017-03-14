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

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParseWithBrace() throws Exception {
      String sourceCode = "(SomeType) someparam;";

      TypeExpressionImpl typeOperation = new TypeExpressionImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, ";");

      Assert.assertEquals(typeOperation.getName(), "someparam");
      Assert.assertEquals(typeOperation.getType(), "SomeType");
   }

   @Test
   public void testParseWithOriginParam() {
      String sourceCode = "someparam1 + someparam2";

      TypeExpressionImpl typeOperation = new TypeExpressionImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, " + someparam2");
      Assert.assertEquals(typeOperation.getName(), "someparam1");
   }

   @Test
   public void testParseWithOrigin() {
      String sourceCode = "12";

      TypeExpressionImpl typeOperation = new TypeExpressionImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, "");
      Assert.assertEquals(typeOperation.getName(), "12");

      sourceCode = "abc;";

      typeOperation = new TypeExpressionImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, ";");
      Assert.assertEquals(typeOperation.getName(), "abc");
   }
}
