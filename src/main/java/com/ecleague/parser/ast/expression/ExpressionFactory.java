package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class ExpressionFactory {

   private static final String NEW_EXPRESSION = "^new [A-Za-z][A-Za-z0-9]*";
   private static final String CAST_EXPRESSION =
         "^\\( *[A-Za-z][A-Za-z0-9]* *\\) *[A-Za-z][A-Za-z0-9]*";

   public static Expression getExpression(String sourceCode) {
      sourceCode = StringUtils.trimToEmpty(sourceCode);
      if ((Pattern.compile(NEW_EXPRESSION).matcher(sourceCode)).find()) {
         return new NewExpressionImpl();
      } else if (sourceCode.startsWith("\"")){
         return new StringExpressionImpl();
      }else if (Pattern.compile(CAST_EXPRESSION).matcher(sourceCode).find()) {
         return new CastExpressionImpl();
      } else if ((Pattern.compile(Regex.NUMBERS).matcher(sourceCode)).find()) {
         return new NumberExpressionImpl();
      } else if (sourceCode.startsWith("(")) {
         return new ExpressionImpl();
      } else if (sourceCode.startsWith(KeyWord.TRUE)
            || sourceCode.startsWith(KeyWord.FALSE)
            || sourceCode.startsWith(Operators.NOT)) {
         return new BoolExpressionImpl();
      } else if (Pattern.compile(Regex.FUNCTION_CALL).matcher(sourceCode).find()
            || Pattern.compile(Regex.PROPERTY_CALL).matcher(sourceCode)
                  .find()) {
         return new ExecuteExpressionImpl();
      } else if (Pattern.compile(Regex.PARAM).matcher(sourceCode).find()) {
         return new TypeExpressionImpl();
      } else if (StringUtils.isEmpty(sourceCode)) {
         return new EmptyExpression();
      }

      throw new ParseSyntaxException(Expression.class, sourceCode);
   }
}
