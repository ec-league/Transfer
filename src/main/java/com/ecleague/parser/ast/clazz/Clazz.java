package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.Operator;
import com.ecleague.parser.ast.SourceParser;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.statement.Statement;
import com.ecleague.parser.ast.statement.UsingStatement;
import com.ecleague.parser.ast.util.Regex;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class Clazz implements SourceParser {

   private List<String> namespaces;
   private List<UsingStatement> usingStatements;

   private String qualifier;
   private boolean staticClass;
   private boolean sealedClass;
   private boolean abstractClass;
   private boolean partialClass;
   private String name;

   private List<String> parents;

   private List<Block> blocks;

   /**
    * One file may contains many classes;
    */
   private Clazz next;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      while (temp.startsWith(KeyWord.USING)) {
         setUsingStatements(new ArrayList<UsingStatement>());

         UsingStatement statement = new UsingStatement();
         temp = statement.parse(temp);
         temp = StringUtils.trimToEmpty(temp);
      }

      if (temp.startsWith(KeyWord.NAMESPACE)) {
         temp = Util.trimTarget(temp, KeyWord.NAMESPACE);

         setNamespaces(new ArrayList<String>());
         temp = Util.processList(temp, getNamespaces(), ".");
      }

      temp = Util.trimTarget(temp, Operators.LEFT_BRACE);

      temp = parseClazz(temp);

      return Util.trimTarget(temp, Operators.RIGHT_BRACE);
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

   public String parseClazz(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      temp = processQualifier(temp);

      temp = processDecoration(temp);

      temp = StringUtils.trimToEmpty(temp);

      if (temp.startsWith(KeyWord.CLASS)) {
         temp = Util.trimTarget(temp, KeyWord.CLASS);

         Matcher matcher;

         if ((matcher = Pattern.compile(Regex.TYPE).matcher(temp)).find()) {
            setName(matcher.group());
            temp = Util.trimTarget(temp, getName());

            if (temp.startsWith(Operators.COLON)) {
               temp = Util.trimTarget(temp, Operators.COLON);
               setParents(new ArrayList<String>());
               temp = Util.processList(temp, getParents(), Operators.COMMA);
            }
         } else {
            throw new ParseSyntaxException(this, sourceCode);
         }


      } else {
         throw new ParseSyntaxException(this, sourceCode);
      }

      temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
      setBlocks(new ArrayList<Block>());
      while (!temp.startsWith(Operators.RIGHT_BRACE)) {
         Block block = new BlockImpl();
         temp = block.parse(temp);
         getBlocks().add(block);
         temp = StringUtils.trimToEmpty(temp);
      }

      temp = Util.trimTarget(temp, Operators.RIGHT_BRACE);

      if (temp.startsWith(Operators.RIGHT_BRACE))
         return temp;
      else {
         setNext(new Clazz());
         getNext().setNamespaces(getNamespaces());
         getNext().setUsingStatements(getUsingStatements());
         return getNext().parseClazz(temp);
      }
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

   private String processDecoration(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      if (temp.startsWith(KeyWord.STATIC)) {
         setStaticClass(true);
         temp = Util.trimTarget(temp, KeyWord.STATIC);
      }

      if (temp.startsWith(KeyWord.ABSTRACT)) {
         setAbstractClass(true);
         temp = Util.trimTarget(temp, KeyWord.ABSTRACT);
      }

      if (temp.startsWith(KeyWord.PARTIAL)) {
         setPartialClass(true);
         temp = Util.trimTarget(temp, KeyWord.PARTIAL);
      }

      if (temp.startsWith(KeyWord.SEALED)) {
         setSealedClass(true);
         temp = Util.trimTarget(temp, KeyWord.SEALED);
      }

      return temp;
   }

   public String getQualifier() {
      return qualifier;
   }

   public void setQualifier(String qualifier) {
      this.qualifier = qualifier;
   }

   public List<String> getNamespaces() {
      return namespaces;
   }

   public void setNamespaces(List<String> namespaces) {
      this.namespaces = namespaces;
   }

   public List<UsingStatement> getUsingStatements() {
      return usingStatements;
   }

   public void setUsingStatements(List<UsingStatement> usingStatements) {
      this.usingStatements = usingStatements;
   }

   public Clazz getNext() {
      return next;
   }

   public void setNext(Clazz next) {
      this.next = next;
   }

   public boolean isStaticClass() {
      return staticClass;
   }

   public void setStaticClass(boolean staticClass) {
      this.staticClass = staticClass;
   }

   public boolean isSealedClass() {
      return sealedClass;
   }

   public void setSealedClass(boolean sealedClass) {
      this.sealedClass = sealedClass;
   }

   public boolean isAbstractClass() {
      return abstractClass;
   }

   public void setAbstractClass(boolean abstractClass) {
      this.abstractClass = abstractClass;
   }

   public boolean isPartialClass() {
      return partialClass;
   }

   public void setPartialClass(boolean partialClass) {
      this.partialClass = partialClass;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Block> getBlocks() {
      return blocks;
   }

   public void setBlocks(List<Block> blocks) {
      this.blocks = blocks;
   }

   public List<String> getParents() {
      return parents;
   }

   public void setParents(List<String> parents) {
      this.parents = parents;
   }
}
