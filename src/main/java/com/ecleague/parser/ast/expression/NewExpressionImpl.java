package com.ecleague.parser.ast.expression;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

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
      StringBuilder sb = new StringBuilder(KeyWord.NEW).append(" ");
      return sb.append(getNext().toJavaCode()).toString();
   }

   public ExecuteExpressionImpl getNext() {
      return next;
   }

   public void setNext(ExecuteExpressionImpl next) {
      this.next = next;
   }
}
