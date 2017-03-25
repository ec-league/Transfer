package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class NewExpressionImpl extends AbstractExpression
      implements Expression {
   private ExecuteExpressionImpl next;

   @Override
   public String getExpressionType() {
      return getNext().getExpressionType();
   }

   @Override
   public String parse(String sourceCode) {

      String temp = StringUtils.trimToEmpty(sourceCode);
      temp = Util.trimTarget(temp, KeyWord.NEW);

      if ((Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         setNext(new ExecuteExpressionImpl());

         return getNext().parse(temp);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }
   }

   /**
    * Take the ast element to Java code.
    *
    * @return
    */
   @Override
   public String toJavaCode() {
      return null;
   }

   public ExecuteExpressionImpl getNext() {
      return next;
   }

   public void setNext(ExecuteExpressionImpl next) {
      this.next = next;
   }
}
