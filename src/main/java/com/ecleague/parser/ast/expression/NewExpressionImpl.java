package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.function.ConstructorFunction;
import com.ecleague.parser.ast.function.Function;
import com.ecleague.parser.ast.util.Regex;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class NewExpressionImpl implements Expression {
   private Function function;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.OBJECT;
   }

   @Override
   public String parse(String sourceCode) {

      String temp = StringUtils.trimToEmpty(sourceCode.substring(3));

      if ((Pattern.compile(Regex.PARAM + "+").matcher(temp)).find()) {
         function = new ConstructorFunction();
         return function.parse(temp);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }
   }
}
