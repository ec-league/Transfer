package com.ecleague.parser.ast.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yun.li on 2017/3/17.
 */
public class FormatTest {

    @Test
    public void formatOriginCode() throws Exception {
        String sourceCode = "       using System.Globalization;\n" +
                "using \n" +
                "\n" +
                "\n" +
                "System.Text.RegularExpressions;\n" +
                "using x = Corp.Common.Utility.Extensions; ";

        sourceCode += "namespace Corp.CorpUserMsg.Entity\n" +
                "{\n" +
                "    /// <summary>\n" +
                "    ///  消息参数实体类\n" +
                "    /// </summary>\n" +
                "    public class SendMessageParaEntity\n" +
                "    {\n" +
                "        /// <summary>\n" +
                "        /// 订单ID\n" +
                "        /// </summary>\n" +
                "        public long OrderID { get; set; }" +
                "}" +
                "}" ;

        sourceCode += "if(x == 1){}";
        sourceCode += "if(x == 2) " +
                "return 2;";

        sourceCode += "if(x == 3 ||(x == 4)) " +
                "return 3;";
        Boolean formatResult = PreFormat.formatOriginFlie(sourceCode);

        Assert.assertEquals(formatResult, true);

    }

}