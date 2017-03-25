package com.ecleague.parser.ast.util;

import org.junit.Test;

/**
 * Created by yun.li on 2017/3/17.
 */
public class FormatTest {

    @Test
    public void formatOriginCode() throws Exception {
//        String sourceCode = "       using System.Globalization;\n" +
//                "using \n" +
//                "\n" +
//                "\n" +
//                "System.Text.RegularExpressions;\n" +
//                "using x = Corp.Common.Utility.Extensions; ";
//
//        sourceCode += "namespace Corp.CorpUserMsg.Entity\n" +
//                "{\n" +
//                "    /// <summary>\n" +
//                "    ///  消息参数实体类\n" +
//                "    /// </summary>\n" +
//                "    public class SendMessageParaEntity\n" +
//                "    {\n" +
//                "        /// <summary>\n" +
//                "        /// 订单ID\n" +
//                "        /// </summary>\n" +
//                "        public long OrderID { get; set; }" +
//
//
//
//
//                                "}" +
//                "}" ;
//
//        sourceCode += "if(x == 1){}";
//        sourceCode += "if(x == 2) " +
//                "return 2;";
//
//        sourceCode += "#region wewejkwehk" + "\n";
//        sourceCode += "int s = 1;";
//        sourceCode += "#endregion" + "\n";
//
//        sourceCode += "\n" +
//                "/**\n" +
//                " * Created by yun.li on 2017/3/17.\n" +
//                " */";
//        sourceCode += "if(x == 3 ||(x == 4)) " +
//                "return 3;";
//
//        sourceCode += " ~First()                    // 析构函数\n" +
//                "            {\n" +
//                "                Console.WriteLine(\"~First()析构函数\");\n" +
//                "            }";
//        BlockImpl block = new BlockImpl();
//        Assert.assertEquals(block.parse(sourceCode), "");

    }

    @Test
    public void strTest() throws Exception {
        String tst = "12345678";
        String temp = tst.substring(2,3);
        System.out.println(tst);

    }

}