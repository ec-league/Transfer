package com.ecleague.parser.ast.expression;

import java.util.ArrayList;
import java.util.List;
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

   private List<String> templateNames;

   private List<Expression> params;

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
            String templates = s.substring(s.indexOf(Operators.LT) + 1,
                  s.indexOf(Operators.GT));
            setTemplateNames(new ArrayList<String>());
            Util.processList(templates, getTemplateNames(), Operators.COMMA);
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
      StringBuilder sb = new StringBuilder();

      sb.append(getSignature());

      if (getTemplateNames() != null && !getTemplateNames().isEmpty()) {
         sb.append(Operators.LT);
         List<String> templateNames1 = getTemplateNames();
         for (int i = 0; i < templateNames1.size(); i++) {
            String templateName = templateNames1.get(i);
            sb.append(templateName);

            if (i != templateNames1.size() - 1)
               sb.append(", ");
         }
         sb.append(Operators.GT);
      }

      sb.append(Operators.LEFT_BRACKET);

      if (getParams() != null && !getParams().isEmpty()) {
         for (int i = 0; i < getParams().size(); i++) {
            sb.append(getParams().get(i).toJavaCode());

            if (i != getParams().size() - 1)
               sb.append(", ");
         }
      }

      sb.append(Operators.RIGHT_BRACKET);

      if (getNext() == null)
         return sb.toString();
      else
         return sb.append(".").append(getNext().toJavaCode()).toString();
   }

   /**
    * Process function params.
    * 
    * @param sourceCode
    * @return
    */
   private String processParams(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      setParams(new ArrayList<Expression>());
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
         getParams().add(e);
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
         getParams().add(e);
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

   public List<String> getTemplateNames() {
      return templateNames;
   }

   public void setTemplateNames(List<String> templateNames) {
      this.templateNames = templateNames;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public List<Expression> getParams() {
      return params;
   }

   public void setParams(List<Expression> params) {
      this.params = params;
   }
}
