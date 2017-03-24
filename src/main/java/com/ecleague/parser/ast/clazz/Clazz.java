package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.SourceParser;
import com.ecleague.parser.ast.function.Function;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class Clazz implements SourceParser {
   private List<Function> functions;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      return null;
   }
}
