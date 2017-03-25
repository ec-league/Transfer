package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * @author EthanPark <br/>
 *         Date: 2017/3/25 <br/>
 * @version 1.0
 * @email byp5303628@hotmail.com
 */
public class StringExpressionImpl extends TypeExpressionImpl {

   private String value;

   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);
      getParamType().setParamType("string");

      if (temp.startsWith("\"")){
         temp = Util.trimTarget(temp, "\"");

         int start = 0;
         while (true){
            int pos = temp.indexOf("\\\"", start);
            if (pos == -1)
               break;
            start = pos + 2;
         }

         setValue(temp.substring(0, temp.indexOf("\"", start == 0 ? 0 : start)));
         temp = Util.trimTarget(temp, getValue());
         temp = Util.trimTarget(temp, "\"");
         return temp;
      }

      throw new ParseSyntaxException(this, sourceCode);
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
