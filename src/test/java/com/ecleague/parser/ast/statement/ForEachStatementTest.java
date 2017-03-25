package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

import com.ecleague.parser.ast.util.PreFormat;

/**
 * ForEachStatement Tester.
 * 
 * @author EthanPark
 * 
 * @version 1.0
 */
public class ForEachStatementTest {


   /**
    * 
    * Method: parse(String sourceCode)
    * 
    */
   @Test
   public void testParse() throws Exception {
      String sourceCode = "foreach(var model in models){ return 5;}";

      ForEachStatement statement = new ForEachStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertEquals(statement.getIter().getParamName(), "model");

      sourceCode = "foreach(var model in s.split()){ return ; }";

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertEquals(statement.getIter().getParamName(), "model");
   }

   @Test
   public void testParseComplex() {
      String sourceCode = " foreach (var upgradeProduct in entry.Value)\n"
            + "                {\n"
            + "                    if (!IsProductMatch(upgradeProduct, entity))\n"
            + "                        continue;\n" + "\n"
            + "                    result.Add(upgradeProduct);\n"
            + "                    break;\n" + "                }";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);

      ForEachStatement statement = new ForEachStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");
   }
}
