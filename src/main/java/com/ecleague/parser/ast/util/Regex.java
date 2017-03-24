package com.ecleague.parser.ast.util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class Regex {
   public static final String TYPE = "^[_A-Za-z][_A-Za-z0-9]*";
   public static final String PARAM = "^[_A-Za-z][_A-Za-z0-9]*";
   public static final String NUMBERS = "^[0-9]+[fFlLmM]{0,1}";

   public static final String FUNCTION_CALL = PARAM + "\\(";
   public static final String PROPERTY_CALL = PARAM + "\\.";

   public static final String VARIABLE =
         "^[[_A-Za-z][A-Za-z0-9_]*|[0-9]+[fFlLmM]{0,1}]";
   public static final String PARAM_VARIABLE =
         "^[_A-Za-z][A-Za-z0-9_]* *[_A-Za-z][_A-Za-z0-9]*";
}
