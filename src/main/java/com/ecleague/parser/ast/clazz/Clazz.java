package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.SourceParser;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.statement.Statement;
import com.ecleague.parser.ast.statement.UsingStatement;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class Clazz implements SourceParser {
   private List<String> namespaceList;
   private List<Statement> statementList;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      return parseClazz(sourceCode);

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
      UsingStatement statement;
      statementList = new ArrayList<>();
      while (temp.startsWith(KeyWord.USING)) {
         statement = new UsingStatement();
         temp = statement.parse(temp);
         statementList.add(statement);
      }

      //handle namespace
      if (temp.startsWith(KeyWord.NAMESPACE)) {

         int beginOfNamespace = temp.indexOf(KeyWord.NAMESPACE);
         int endOfNamespace = temp.indexOf(Operators.LEFT_BRACE);
         temp = Util.trimTarget(temp, KeyWord.NAMESPACE);
         String namespace = temp.substring(beginOfNamespace, endOfNamespace);
         String[] namespaceArray = namespace.split(".");
         for (String name : namespaceArray) {
            namespaceList.add(name);
         }
         StringBuilder strBuilder = new StringBuilder(temp);
         strBuilder.delete(beginOfNamespace, endOfNamespace);
         temp = strBuilder.toString();
      }

      return temp;

      //sealed\abstract\static\partial
      // :

      //domain or function
      //constructor\static constructor

   }
}
