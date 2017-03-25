package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yun.li on 2017/3/25.
 */
public class IfStatementTest {
   @Test
   public void parse() throws Exception {
      String sourceCode = "if (x==2)" + "                {"
            + "                    return 5;" + "                }"
            + "                else " + "                    if (x==3)"
            + "                {" + "                    return 7;"
            + "                }" + "                    else if (x == 3)"
            + "                    {" + "                        return 11;"
            + "                    }" + "                    else"
            + "                    {" + "                        return 15;"
            + "                    }";

      IfStatement statement = new IfStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");

      Assert.assertNotNull(statement.getIfExpression());
   }

}