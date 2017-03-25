package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark <br/>
 *         Date: 2017/3/25 <br/>
 * @version 1.0
 * @email byp5303628@hotmail.com
 */
public class FieldBlock implements Block {

   private String filedName;
   private String returnType;

   private Expression initValue;

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
         setFiledName(matcher.group());
         temp = Util.trimTarget(temp, getFiledName());
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      if (temp.startsWith(Operators.ASSIGN)) {
         temp = Util.trimTarget(temp, Operators.ASSIGN);
         Expression e = new ExpressionImpl();
         setInitValue(e);
         return e.parse(temp);
      } else if (temp.startsWith(Operators.SEMICOLON)) {
         setInitValue(null);
         return Util.trimTarget(temp, Operators.SEMICOLON);
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

   public String getFiledName() {
      return filedName;
   }

   public void setFiledName(String filedName) {
      this.filedName = filedName;
   }

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public Expression getInitValue() {
      return initValue;
   }

   public void setInitValue(Expression initValue) {
      this.initValue = initValue;
   }
}
