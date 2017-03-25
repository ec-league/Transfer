package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

import com.ecleague.parser.ast.util.PreFormat;

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

   @Test
   public void testParse() {
      String sourceCode = "if (!IsProductMatch(upgradeProduct, entity))\n"
            + "                        continue;";
      sourceCode = PreFormat.removeUnusedInfo(sourceCode);
      IfStatement statement = new IfStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");
   }

   @Test
   public void testParse1() {
      String sourceCode =
            " if (!product.MatchFlightNo(entity.DepartMainFlightNo))\n"
                  + "                return false;";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);

      IfStatement statement = new IfStatement();

      Assert.assertEquals(statement.parse(sourceCode), "");
   }

}