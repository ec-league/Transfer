package com.ecleague.parser.ast.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class ExecuteExpressionImpl extends AbstractExpression
      implements Expression {

   private ExecuteExpressionImpl next;

   private String templateName;

   private String signature;

   @Override
   public String getExpressionType() {
      return null;
   }

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.FUNCTION_CALL).matcher(temp))
            .find()) {
         String s = matcher.group();
         s = s.substring(0, s.length() - 1);

         if (s.indexOf(Operators.LT) != -1) {
            setSignature(s.substring(0, s.indexOf(Operators.LT)));
            setTemplateName(s.substring(s.indexOf(Operators.LT) + 1,
                  s.indexOf(Operators.GT)));
         } else {
            setSignature(s);
         }
         temp = Util.trimTarget(temp, s);

         temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

         if (temp.startsWith(Operators.RIGHT_BRACKET)) {
            temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);

            if (temp.startsWith(".")) {
               temp = temp.substring(1);
               next = new ExecuteExpressionImpl();
               return next.parse(temp);
            }

            return temp;
         } else {
            temp = processParams(temp);
            temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
            if (temp.startsWith(".")) {
               temp = Util.trimTarget(temp, ".");
               setNext(new ExecuteExpressionImpl());
               return getNext().parse(temp);
            }
            return temp;
         }
      } else if ((matcher = Pattern.compile(Regex.PROPERTY_CALL).matcher(temp))
            .find()) {
         String s = matcher.group();
         s = s.substring(0, s.length() - 1);

         temp = Util.trimTarget(temp, s);
         temp = Util.trimTarget(temp, ".");
         setNext(new ExecuteExpressionImpl());
         return getNext().parse(temp);
      } else if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp))
            .find()) {
         String s = matcher.group();

         temp = Util.trimTarget(temp, s);
         return temp;
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

   /**
    * Process function params.
    * 
    * @param sourceCode
    * @return
    */
   private String processParams(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      Expression e = new ExpressionImpl();
      if (temp.startsWith(KeyWord.OUT)) {
         e.setOut();
         temp = Util.trimTarget(temp, KeyWord.OUT);
      }

      if (temp.startsWith(KeyWord.REF)) {
         e.setRef();
         temp = Util.trimTarget(temp, KeyWord.REF);
      }

      if (Pattern.compile(Regex.VARIABLE).matcher(temp).find()) {
         temp = e.parse(temp);
         temp = StringUtils.trimToEmpty(temp);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      while (temp.startsWith(Operators.COMMA)) {
         temp = Util.trimTarget(temp, Operators.COMMA);

         e = new ExpressionImpl();

         if (temp.startsWith(KeyWord.OUT)) {
            e.setOut();
            temp = Util.trimTarget(temp, KeyWord.OUT);
         }

         if (temp.startsWith(KeyWord.REF)) {
            e.setRef();
            temp = Util.trimTarget(temp, KeyWord.REF);
         }

         temp = e.parse(temp);
         temp = StringUtils.trimToEmpty(temp);
      }

      return temp;
   }

   public ExecuteExpressionImpl getNext() {
      return next;
   }

   public void setNext(ExecuteExpressionImpl next) {
      this.next = next;
   }

   public String getTemplateName() {
      return templateName;
   }

   public void setTemplateName(String templateName) {
      this.templateName = templateName;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }
}
