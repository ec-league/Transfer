package com.ecleague.parser.ast.statement;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by coraline on 17/3/25.
 */
public class SwitchCaseStatementTest {
    @Test
    public void testPreProcess() throws Exception {

        String sourceCode = "switch (s){\n" +
                "            case 1:\n" +
                "                func();\n" +
                "                break;;\n" +
                "            case 2:\n" +
                "                func3();\n" +
                "                break;\n" +
                "                default:\n" +
                "                    break;\n" +
                "        }";


        SwitchCaseStatement statement = new SwitchCaseStatement();

        Assert.assertEquals(statement.parse(sourceCode), "");

        Assert.assertNotNull(statement.getExpression());
    }

}