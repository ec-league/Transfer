package com.ecleague.parser.ast;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/6<br/>
 * Email: byp5303628@hotmail.com
 */
public interface SourceParser {
   /**
    * Take the source code as the param, parse and generate ast object.
    * @param sourceCode
    */
   void parse(String sourceCode);
}
