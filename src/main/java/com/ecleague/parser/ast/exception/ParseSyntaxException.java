package com.ecleague.parser.ast.exception;

import com.ecleague.parser.ast.SourceParser;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class ParseSyntaxException extends RuntimeException {
   public ParseSyntaxException(SourceParser parser, String sourceCode) {
      super(String.format("Parse %s Failed : %s", parser.getClass(),
            sourceCode));
   }
}
