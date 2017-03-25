package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

import com.ecleague.parser.ast.exception.ParseSyntaxException;

/**
 * UsingStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class UsingStatementTest {

   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test(expected = ParseSyntaxException.class)
   public void testParse() throws Exception {
      String sourceCode = "using Ctrip.IntlFlight.DomainModel;";

      UsingStatement statement = new UsingStatement();
      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertEquals(statement.getNamespaces().size(), 3);

      sourceCode =
            "using DomainModel = Ctrip.IntlFlight.DomainModel; using System.Collections.Generic;";

      Assert.assertEquals(statement.parse(sourceCode),
            "using System.Collections.Generic;");

      Assert.assertEquals(statement.getNamespaces().size(), 3);
      Assert.assertEquals(statement.getName(), "DomainModel");

      statement.parse("abc");
   }
}
