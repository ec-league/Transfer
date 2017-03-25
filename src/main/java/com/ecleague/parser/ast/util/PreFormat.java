package com.ecleague.parser.ast.util;

import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.exception.PreFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yun.li on 2017/3/17.
 */
public class PreFormat {

   private static final List<String> acessList = new ArrayList<>();
   static {
      acessList.add("private");
      acessList.add("protected");
      acessList.add("public");
   }

   public static Boolean formatOriginFlie(String source)
         throws PreFormatException {

      //去掉单行三个注释
      Pattern pattern = Pattern.compile("///((.*?))\n");
      Matcher matcher = pattern.matcher(source);

      matcher.matches();//这句话相当重要，没有的话，下面那个while循环会报错，具体原因还没找到
      while (matcher.find()) {
         source = matcher.replaceAll("");
      }

      System.out.println("step0:" + source + "\n");

      //去掉多行注释
      pattern = Pattern.compile("/\\*(.|[\\r\\n])*?\\*/");
      matcher = pattern.matcher(source);
      matcher.matches();
      while (matcher.find()) {
         source = matcher.replaceAll("");

      }
      System.out.println("step1:" + source + "\n");

      //去掉单行注释
      pattern = Pattern.compile("^ *.*//.*$\\n");
      matcher = pattern.matcher(source);
      matcher.matches();
      while (matcher.find()) {
         matcher.group();
         source = matcher.replaceAll("");

      }
      System.out.println("step2:" + source + "\n");

      //去掉region
      pattern = Pattern.compile("#region((.*?))\n");
      matcher = pattern.matcher(source);
      matcher.matches();
      while (matcher.find()) {
         source = matcher.replaceAll("");
      }

      source = source.replaceAll("#endregion", "");

      //去掉析构函数 一个类中最多只有一个
      pattern = Pattern.compile("^ *~\\w+ *\\(\\) *\\{");
      matcher = pattern.matcher(source);
      matcher.matches();
      while (matcher.find()) {
         int matchEnd = matcher.end();
         int matchStart = matcher.start();

         int braceStart = source.indexOf(Operators.LEFT_BRACE, matchEnd);
         int braceEnd = -1;
         int firstRightBrace = source.indexOf(Operators.RIGHT_BRACE, braceStart);

         Boolean hasOtherBrace = source.substring(braceStart, firstRightBrace).contains(Operators.LEFT_BRACE);
         if(hasOtherBrace){
            braceEnd = getBraceEndIndex(source, braceStart);
         }

         //为了按位置删除，用StringBuilder做了下中转
         StringBuilder sourceBuilder = new StringBuilder(source);
         sourceBuilder.delete(matchStart, braceEnd);

         source = sourceBuilder.toString();
      }

      System.out.println("step3:" + source + "\n");
      //把所有的回车替换为空格
      source = source.replaceAll("[\\t|\\r|\\n]", " ");



//      //遇到";"换行
//      source = source.replaceAll("[;]", ";\n");
//      System.out.println("step6:" + source + "\n");
//
//      //遇到"{"和"}"就换行
//      source = source.replaceAll("[{]", "\n{\n");
//      source = source.replaceAll("[}]", "\n}\n");

      return true;


   }

   private static int getBraceEndIndex(String source, int braceStart) {
      int braceEnd = -1;
      int braceLeftCount = 1;
      int braceRightCount = 0;
      for (int index = braceStart + 2; source
              .charAt(index) < source.length(); index++) {
         if (source.charAt(index) != '}' && source.charAt(index) != '{') {
            continue;
         } else if (source.charAt(index) == '{') {
            braceLeftCount++;
         } else if (source.charAt(index) == '}') {
            braceRightCount++;
            if(braceLeftCount == braceRightCount - 1){
               braceEnd = index;
            }
         }
      }
      return braceEnd;
   }

   private static int getInsertIndex(StringBuilder source, String matchStr,
         int begin) {
      int ifIndex = source.indexOf(matchStr, begin);

      if (ifIndex == -1) {
         return -1;
      }
      int firstSemicolonAfterIf = source.indexOf(";", ifIndex);

      Boolean isIfHasBrace =
            source.substring(ifIndex, firstSemicolonAfterIf).contains("{");
      //没接括号的，就应该在最后一个反括号之后换行
      if (isIfHasBrace == false) {
         int preBraceCount = 1;
         int backBraceCount = 0;
         //从if后面开始匹配
         for (int index = ifIndex + 2; source
               .charAt(index) < firstSemicolonAfterIf; index++) {
            if (source.charAt(index) != ')') {
               continue;
            } else if (source.charAt(index) == '(') {
               preBraceCount++;
            } else if (source.charAt(index) == ')'
                  && backBraceCount == preBraceCount - 1) {

               return index;
            }

         }
      }
      return -1;
   }
}
