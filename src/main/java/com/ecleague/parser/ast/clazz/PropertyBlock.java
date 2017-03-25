package com.ecleague.parser.ast.clazz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.statement.Statement;
import com.ecleague.parser.ast.statement.StatementFactory;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

/**
 * @author EthanPark <br/>
 *         Date: 2017/3/25 <br/>
 * @version 1.0
 * @email byp5303628@hotmail.com
 */
public class PropertyBlock implements Block {
   private String propertyName;
   private String returnType;

   private List<Statement> getStatements;
   private List<Statement> setStatements;

   private String getQualifier;
   private String setQualifier;

   private BlockImpl parent;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         setPropertyName(matcher.group());
         temp = Util.trimTarget(temp, getPropertyName());
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      if (temp.startsWith(Operators.LEFT_BRACE)) {
         temp = Util.trimTarget(temp, Operators.LEFT_BRACE);

         temp = processSetAndGet(temp);

         temp = StringUtils.trimToEmpty(temp);

         if (!temp.startsWith(Operators.RIGHT_BRACE))
            temp = processSetAndGet(temp);

         temp = Util.trimTarget(temp, Operators.RIGHT_BRACE);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      return temp;
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

   private String processSetAndGet(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (temp.startsWith(KeyWord.PUBLIC)) {
         setSetQualifier(KeyWord.PUBLIC);
         temp = Util.trimTarget(temp, KeyWord.PUBLIC);
      } else if (temp.startsWith(KeyWord.PRIVATE)) {
         setSetQualifier(KeyWord.PRIVATE);
         temp = Util.trimTarget(temp, KeyWord.PRIVATE);
      } else if (temp.startsWith(KeyWord.PROTECTED)) {
         setSetQualifier(KeyWord.PROTECTED);
         temp = Util.trimTarget(temp, KeyWord.PROTECTED);
      }

      if (temp.startsWith("set")) {
         temp = Util.trimTarget(temp, "set");
         if (temp.startsWith(Operators.SEMICOLON)) {
            temp = Util.trimTarget(temp, Operators.SEMICOLON);
         } else if (temp.startsWith(Operators.LEFT_BRACE)) {
            temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
            setSetStatements(new ArrayList<Statement>());
            temp = StatementFactory.processBlock(temp, getSetStatements());
            temp = Util.trimTarget(temp, Operators.RIGHT_BRACE);
         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }

         if (getSetQualifier() == null) {
            setSetQualifier(getParent().getQualifier());
         }
      } else if (temp.startsWith("get")) {
         temp = Util.trimTarget(temp, "get");
         if (temp.startsWith(Operators.SEMICOLON)) {
            temp = Util.trimTarget(temp, Operators.SEMICOLON);
         } else if (temp.startsWith(Operators.LEFT_BRACE)) {
            temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
            setGetStatements(new ArrayList<Statement>());
            temp = StatementFactory.processBlock(temp, getGetStatements());
            temp = Util.trimTarget(temp, Operators.RIGHT_BRACE);
         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }

         if (getGetQualifier() == null) {
            setGetQualifier(getParent().getQualifier());
         }
      } else {
         return sourceCode;
      }


      return temp;
   }

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public String getPropertyName() {
      return propertyName;
   }

   public void setPropertyName(String propertyName) {
      this.propertyName = propertyName;
   }

   public List<Statement> getGetStatements() {
      return getStatements;
   }

   public void setGetStatements(List<Statement> getStatements) {
      this.getStatements = getStatements;
   }

   public List<Statement> getSetStatements() {
      return setStatements;
   }

   public void setSetStatements(List<Statement> setStatements) {
      this.setStatements = setStatements;
   }


   public String getGetQualifier() {
      return getQualifier;
   }

   public void setGetQualifier(String getQualifier) {
      this.getQualifier = getQualifier;
   }

   public String getSetQualifier() {
      return setQualifier;
   }

   public void setSetQualifier(String setQualifier) {
      this.setQualifier = setQualifier;
   }

   public BlockImpl getParent() {
      return parent;
   }

   public void setParent(BlockImpl parent) {
      this.parent = parent;
   }
}
