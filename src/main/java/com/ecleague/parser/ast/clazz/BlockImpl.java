package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author EthanPark
 * @email byp5303628@hotmail.com
 * @since 2017/3/24
 */
public class BlockImpl implements Block {
   private boolean staticBlock;
   private boolean readonlyBlock;
   private boolean constBlock;
   private String qualifier;
   private String type;
   private String signature;

   private List<ParamType> paramTypeList;
   private Expression initValue;

   @Override
   public String parse(String sourceCode) {
      String temp = processQualifier(sourceCode);

      temp = processDecoration(temp);

      temp = processType(temp);

      temp = processSignature(temp);

      if (temp.startsWith(Operators.LEFT_BRACKET)) {
         // function
         temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

         if (!temp.startsWith(Operators.RIGHT_BRACKET)) {
            temp = processParams(temp);
         }
         temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
      } else if (temp.startsWith(Operators.LEFT_BRACE)) {
         // property
      } else if (temp.startsWith(Operators.ASSIGN)) {
         // field with initialization
         temp = Util.trimTarget(temp, Operators.ASSIGN);
         initValue = new ExpressionImpl();
         temp = initValue.parse(temp);
      } else if (temp.startsWith(Operators.SEMICOLON)) {
         // field without initialization
         temp = Util.trimTarget(temp, Operators.SEMICOLON);
      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      return temp;
   }

   private String processParams(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      paramTypeList = new ArrayList<>();

      while (!temp.startsWith(Operators.COMMA)) {
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
         paramTypeList.add(paramType);
         temp = StringUtils.trimToEmpty(temp);
      }

      return temp;
   }

   private String processSignature(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         setType(matcher.group());
         return Util.trimTarget(temp, getType());
      }
      throw new ParseSyntaxException(this, sourceCode);
   }

   private String processType(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
         setType(matcher.group());
         return Util.trimTarget(temp, getType());
      }
      throw new ParseSyntaxException(this, sourceCode);
   }

   private String processDecoration(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      if (temp.startsWith(KeyWord.STATIC)) {
         setStaticBlock(true);
         temp = Util.trimTarget(temp, KeyWord.STATIC);
      }

      if (temp.startsWith(KeyWord.READONLY)) {
         setReadonlyBlock(true);
         temp = Util.trimTarget(temp, KeyWord.READONLY);
      }

      if (temp.startsWith(KeyWord.CONST)) {
         setConstBlock(true);
         temp = Util.trimTarget(temp, KeyWord.CONST);
      }

      return temp;
   }

   private String processQualifier(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      if (temp.startsWith(KeyWord.PRIVATE)) {
         setQualifier(KeyWord.PRIVATE);
         temp = Util.trimTarget(temp, KeyWord.PRIVATE);
      }
      if (temp.startsWith(KeyWord.PUBLIC)) {
         setQualifier(KeyWord.PUBLIC);
         temp = Util.trimTarget(temp, KeyWord.PUBLIC);
      }
      if (temp.startsWith(KeyWord.INTERNAL)) {
         setQualifier(KeyWord.INTERNAL);
         temp = Util.trimTarget(temp, KeyWord.INTERNAL);
      }
      if (temp.startsWith(KeyWord.PROTECTED)) {
         setQualifier(KeyWord.PROTECTED);
         temp = Util.trimTarget(temp, KeyWord.PROTECTED);
      }

      return temp;
   }

   public boolean isStaticBlock() {
      return staticBlock;
   }

   public void setStaticBlock(boolean staticBlock) {
      this.staticBlock = staticBlock;
   }

   public boolean isReadonlyBlock() {
      return readonlyBlock;
   }

   public void setReadonlyBlock(boolean readonlyBlock) {
      this.readonlyBlock = readonlyBlock;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public String getQualifier() {
      return qualifier;
   }

   public void setQualifier(String qualifier) {
      this.qualifier = qualifier;
   }

   public boolean isConstBlock() {
      return constBlock;
   }

   public void setConstBlock(boolean constBlock) {
      this.constBlock = constBlock;
   }
}
