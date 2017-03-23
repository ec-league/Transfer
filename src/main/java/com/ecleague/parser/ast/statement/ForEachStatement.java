package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.ParamType;
import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionFactory;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/16<br/>
 * Email: byp5303628@hotmail.com
 */
public class ForEachStatement implements Statement {
   private ParamType iter;
   private Expression collection;

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    * @return left source code.
    */
   @Override
   public String parse(String sourceCode) {
      String temp = StringUtils.trimToEmpty(sourceCode);

      if (!temp.startsWith(KeyWord.FOREACH)) {
         throw new ParseSyntaxException(this, sourceCode);
      }

      temp = Util.trimTarget(temp, KeyWord.FOREACH);

      //(var a in b)
      temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

      iter = new ParamType();

      temp = iter.parse(temp);

      temp = Util.trimTarget(temp, KeyWord.IN);

      collection = ExpressionFactory.getExpression(temp);
      temp = collection.parse(temp);

      temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);

      return temp;
   }

   public ParamType getIter() {
      return iter;
   }

   public void setIter(ParamType iter) {
      this.iter = iter;
   }

   public Expression getCollection() {
      return collection;
   }

   public void setCollection(Expression collection) {
      this.collection = collection;
   }
}
