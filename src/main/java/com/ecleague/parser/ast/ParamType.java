package com.ecleague.parser.ast;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class ParamType implements SourceParser {
   private String paramType;
   private String paramName;
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
      } else if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
         setParamType(matcher.group());
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      temp = Util.trimTarget(temp, getParamType());

      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         setParamName(matcher.group());
      }

      return Util.trimTarget(temp, getParamName());
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
