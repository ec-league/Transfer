package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public interface SourceParser {
   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   String parse(String sourceCode);

   /**
    * Take the ast element to Java code.
    *
    * @return
    */
   String toJavaCode();
}
