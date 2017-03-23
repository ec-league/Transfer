package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.exception.ParseSyntaxException;
import org.junit.Assert;
import org.junit.Test;

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

      sourceCode = "using DomainModel = Ctrip.IntlFlight.DomainModel; abc";

      Assert.assertEquals(statement.parse(sourceCode), "abc");

      Assert.assertEquals(statement.getNamespaces().size(), 3);
      Assert.assertEquals(statement.getName(), "DomainModel");

      statement.parse("abc");
   }
}
