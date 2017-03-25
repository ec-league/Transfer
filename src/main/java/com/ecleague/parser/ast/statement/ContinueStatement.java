package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * @author EthanPark <br/>
 *         Date: 2017/3/25 <br/>
 * @version 1.0
 * @email byp5303628@hotmail.com
 */
public class ContinueStatement implements Statement {

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(KeyWord.CONTINUE)) {
         temp = Util.trimTarget(temp, KeyWord.CONTINUE);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      return Util.trimTarget(temp, Operators.SEMICOLON);
   }
}
