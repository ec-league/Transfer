package com.ecleague.parser.ast.operation;

import org.junit.Assert;
import org.junit.Test;

/**
 * TypeOperationImpl Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class TypeOperationImplTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParseWithBrace() throws Exception {
      String sourceCode = "(SomeType) someparam;";

      TypeOperationImpl typeOperation = new TypeOperationImpl();

      sourceCode = typeOperation.parse(sourceCode);

      Assert.assertEquals(sourceCode, ";");

      Assert.assertEquals(typeOperation.getName(), "someparam");
      Assert.assertEquals(typeOperation.getType(), "SomeType");
   }
}
