package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.statement.Statement;
import com.ecleague.parser.ast.statement.StatementFactory;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 *         Date: 2017/3/25 <br/>
 * @version 1.0
 * @email byp5303628@hotmail.com
 */
public class FunctionBlock implements Block {

   private List<ParamType> paramTypeList;

   private String signature;

   private String returnType;

   private List<Statement> innerStatements;

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
         setSignature(matcher.group());
         temp = Util.trimTarget(temp, getSignature());
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      if (temp.startsWith(Operators.LEFT_BRACKET)) {
         temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      if (!temp.startsWith(Operators.RIGHT_BRACKET)) {
         temp = processParams(temp);
      }
      temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);

      setInnerStatements(new ArrayList<Statement>());

      if (temp.startsWith(Operators.LEFT_BRACE)) {
         temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
         temp = StatementFactory.processBlock(temp, innerStatements);
         return Util.trimTarget(temp, Operators.RIGHT_BRACE);
      } else {
         Statement statement = StatementFactory.getStatement(sourceCode);
         return statement.parse(temp);
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

   private String processParams(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      setParamTypeList(new ArrayList<ParamType>());
      while (true) {
         ParamType paramType = new ParamType();

         if (temp.startsWith(KeyWord.OUT)) {
            paramType.setOut(true);
            temp = Util.trimTarget(temp, KeyWord.OUT);
         }

         if (temp.startsWith(KeyWord.REF)) {
            paramType.setRef(true);
            temp = Util.trimTarget(temp, KeyWord.REF);
         }
         temp = paramType.parse(temp);
         getParamTypeList().add(paramType);
         temp = StringUtils.trimToEmpty(temp);

         if (!temp.startsWith(Operators.COMMA))
            break;
         temp = Util.trimTarget(temp, Operators.COMMA);
      }

      return temp;
   }

   public List<ParamType> getParamTypeList() {
      return paramTypeList;
   }

   public void setParamTypeList(List<ParamType> paramTypeList) {
      this.paramTypeList = paramTypeList;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public List<Statement> getInnerStatements() {
      return innerStatements;
   }

   public void setInnerStatements(List<Statement> innerStatements) {
      this.innerStatements = innerStatements;
   }
}
