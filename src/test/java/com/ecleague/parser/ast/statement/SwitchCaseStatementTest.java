package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by coraline on 17/3/25.
 */
public class SwitchCaseStatementTest {
    @Test
    public void testPreProcess() throws Exception {

        String sourceCode = "switch (s){\n" +
                "            case 1:\n" +
                "                \n" +
                "                return;\n" +
                "            case 2:\n" +
                "                \n" +
                "                return;\n" +
                "                default:\n" +
                "                    return;\n" +
                "        }";


        SwitchCaseStatement statement = new SwitchCaseStatement();

        Assert.assertEquals(statement.parse(sourceCode), "");

        Assert.assertNotNull(statement.getExpression());
    }

}