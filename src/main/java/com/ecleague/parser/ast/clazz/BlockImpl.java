package com.ecleague.parser.ast.clazz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;

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

   private Block subBlock;

   @Override
   public String parse(String sourceCode) {
      String temp = processQualifier(sourceCode);

      temp = processDecoration(temp);

      temp = processType(temp);

      String tempSourceCode = processSignature(temp);

      if (tempSourceCode.startsWith(Operators.LEFT_BRACKET)) {
         FunctionBlock block = new FunctionBlock();
         setSubBlock(block);
         temp = getSubBlock().parse(temp);
         block.setReturnType(getType());
         return temp;
      } else if (tempSourceCode.startsWith(Operators.LEFT_BRACE)) {
         PropertyBlock block = new PropertyBlock();
         block.setParent(this);
         setSubBlock(block);
         temp = getSubBlock().parse(temp);
         block.setReturnType(getType());
         return temp;
      } else if (tempSourceCode.startsWith(Operators.ASSIGN)
            || tempSourceCode.startsWith(Operators.SEMICOLON)) {
         FieldBlock block = new FieldBlock();
         setSubBlock(block);
         block.setReturnType(getType());
         return getSubBlock().parse(temp);
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


   private String processSignature(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      Matcher matcher;
      if ((matcher = Pattern.compile(Regex.PARAM).matcher(temp)).find()) {
         String sig = matcher.group();
         return Util.trimTarget(temp, sig);
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

   public Block getSubBlock() {
      return subBlock;
   }

   public void setSubBlock(Block subBlock) {
      this.subBlock = subBlock;
   }
}
