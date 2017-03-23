package com.ecleague.parser.ast;

import org.junit.Assert;
import org.junit.Test;

/**
 * ParamType Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ParamTypeTest {
   @Test
   public void testParse(){
      String sourceCode = "Abc abc;";

      ParamType paramType = new ParamType();
      Assert.assertEquals(paramType.parse(sourceCode), ";");

      Assert.assertEquals(paramType.getParamName(), "abc");
      Assert.assertEquals(paramType.getParamType(), "Abc");

      sourceCode = "var abc;";

      Assert.assertEquals(paramType.parse(sourceCode), ";");

      Assert.assertEquals(paramType.getParamName(), "abc");
      Assert.assertEquals(paramType.getParamType(), "var");
   }

}
