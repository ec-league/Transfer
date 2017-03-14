package com.ecleague.parser.ast.expression;

import com.ecleague.parser.ast.SourceParser;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public interface Expression extends SourceParser {
   /**
    * Get expression's type.
    * 
    * @return
    */
   ExpressionType getExpressionType();
}
