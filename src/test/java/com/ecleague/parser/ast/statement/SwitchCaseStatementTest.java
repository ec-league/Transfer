package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by coraline on 17/3/25.
 */
public class SwitchCaseStatementTest {
   @Test
   public void testPreProcess() throws Exception {

      String sourceCode = "switch (s){" + "            case 1:"
            + "                " + "                return;"
            + "            case 2:" + "                "
            + "                return;" + "                default:"
            + "                    return;" + "        }";


      SwitchCaseStatement statement = new SwitchCaseStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.getExpression());
   }

   @Test
   public void testToJavaCode() throws Exception {

      String sourceCode = "switch (s){" + "            case 1:"
            + "                " + "                return;"
            + "            case 2:" + "                "
            + "                return;" + "                default:"
            + "                    return;" + "        }";


      SwitchCaseStatement statement = new SwitchCaseStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.toJavaCode());
   }

}