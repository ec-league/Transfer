package com.ecleague.parser.ast.operation;

import com.ecleague.parser.ast.Regex;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class TypeOperationImpl implements Operation {

   private String type;
   private String name;

   @Override
   public String parse(String sourceCode) {
      sourceCode = StringUtils.trimToEmpty(sourceCode);

      if (StringUtils.isEmpty(sourceCode))
         throw new ParseSyntaxException(this, sourceCode);

      if (sourceCode.startsWith(Operators.LEFT_BRACKET)) {
         setType(sourceCode.substring(
               sourceCode.indexOf(Operators.LEFT_BRACKET) + 1,
               sourceCode.indexOf(Operators.RIGHT_BRACKET)));

         sourceCode = sourceCode
               .substring(sourceCode.indexOf(Operators.RIGHT_BRACKET) + 1);

         sourceCode = StringUtils.trimToEmpty(sourceCode);

         Pattern pattern = Pattern.compile(Regex.PARAM + "+");
         Matcher matcher = pattern.matcher(sourceCode);
         if (matcher.find()) {
            setName(matcher.group());
         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }

         return sourceCode
               .substring(sourceCode.indexOf(getName()) + getName().length());
      }

      Pattern pattern = Pattern.compile(Regex.PARAM);
      Matcher matcher = pattern.matcher(sourceCode);

      setName(matcher.group());

      return sourceCode
            .substring(sourceCode.indexOf(getName() + getName().length()));
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = StringUtils.trimToEmpty(type);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
