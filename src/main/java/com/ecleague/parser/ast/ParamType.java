package com.ecleague.parser.ast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class ParamType implements SourceParser {
   private String paramType;
   private String paramName;
   private String templateName;

   private boolean out;
   private boolean ref;

   public String getParamType() {
      return paramType;
   }

   public void setParamType(String paramType) {
      this.paramType = paramType;
   }

   public String getParamName() {
      return paramName;
   }

   public void setParamName(String paramName) {
      this.paramName = paramName;
   }

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {

      String temp = StringUtils.trimToEmpty(sourceCode);

      Matcher matcher;

      if (temp.startsWith(KeyWord.VAR)) {
         setParamType(KeyWord.VAR);
         temp = Util.trimTarget(temp, getParamType());
      } else if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
         String s = matcher.group();
         setParamType(s);
         temp = Util.trimTarget(temp, getParamType());
         if (temp.startsWith(Operators.LT)) {
            temp = Util.trimTarget(temp, Operators.LT);

            if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
               setTemplateName(matcher.group());
               temp = Util.trimTarget(temp, getTemplateName());
            }
            temp = Util.trimTarget(temp, Operators.GT);
         }
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }


      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         setParamName(matcher.group());
      }

      return Util.trimTarget(temp, getParamName());
   }

   public String getTemplateName() {
      return templateName;
   }

   public void setTemplateName(String templateName) {
      this.templateName = templateName;
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

   public boolean isOut() {
      return out;
   }

   public void setOut(boolean out) {
      this.out = out;
   }

   public boolean isRef() {
      return ref;
   }

   public void setRef(boolean ref) {
      this.ref = ref;
   }
}
