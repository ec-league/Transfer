package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/16<br/>
 * Email: byp5303628@hotmail.com
 */
public class UsingStatement implements Statement {
   private List<String> namespaces;
   private String name;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.USING))
         throw new ParseSyntaxException(this, sourceCode);

      temp = Util.trimTarget(temp, KeyWord.USING);

      Matcher matcher;

      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         String firstKey = matcher.group();

         temp = Util.trimTarget(temp, firstKey);

         namespaces = new ArrayList<>();

         if (temp.startsWith(Operators.ASSIGN)) {
            name = firstKey;
            temp = Util.trimTarget(temp, Operators.ASSIGN);

            return processNameSpaces(temp);
         } else if (temp.startsWith(".")) {
            namespaces.add(firstKey);
            temp = Util.trimTarget(temp, ".");
            return processNameSpaces(temp);
         } else if (temp.startsWith(Operators.SEMICOLON)) {
            temp = Util.trimTarget(temp, Operators.SEMICOLON);
            return temp;
         }
      }
      throw new ParseSyntaxException(this, sourceCode);
   }

   private String processNameSpaces(String sourceCode) {
      Matcher matcher;

      while ((matcher = Pattern.compile(Regex.PARAM).matcher(sourceCode))
            .find()) {
         String tempName = matcher.group();
         namespaces.add(tempName);
         sourceCode = Util.trimTarget(sourceCode, tempName);

         if (sourceCode.startsWith(".")) {
            sourceCode = Util.trimTarget(sourceCode, ".");
         }
      }

      if (sourceCode.startsWith(Operators.SEMICOLON)) {
         return Util.trimTarget(sourceCode, Operators.SEMICOLON);
      }
      return sourceCode;
   }

   public List<String> getNamespaces() {
      return namespaces;
   }

   public void setNamespaces(List<String> namespaces) {
      this.namespaces = namespaces;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
