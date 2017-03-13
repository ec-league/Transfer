package com.ecleague.parser.ast.operation;

import org.junit.Assert;
import org.junit.Test;

/**
 * OperationImpl Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class OperationImplTest {
   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      OperationImpl operation = new OperationImpl();

      String sourceCode = "5 + 6;";

      Assert.assertEquals(operation.parse(sourceCode), "");

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "5");
      operation = (OperationImpl) operation.getRight();

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "6");

      sourceCode = "a + b + 7 - 4;";

      Assert.assertEquals(operation.parse(sourceCode), "");

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "a");

      operation = (OperationImpl) operation.getRight();

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "b");

      operation = (OperationImpl) operation.getRight();

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "7");

      operation = (OperationImpl) operation.getRight();

      Assert.assertEquals(((TypeOperationImpl) operation.getLeft()).getName(),
            "4");
   }


}
