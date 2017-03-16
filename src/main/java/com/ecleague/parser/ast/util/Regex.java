package com.ecleague.parser.ast.util;

/**
 * @author EthanPark <br/>
 * @version 1.0
 */
public class Regex {
   public static final String TYPE = "^[A-Za-z][A-Za-z0-9]*";
   public static final String PARAM = "^[A-Za-z][A-Za-z0-9]*";
   public static final String NUMBERS = "^[0-9]+[fFlLmM]{0,1}";

   public static final String FUNCTION_CALL = PARAM + "\\(";
   public static final String PROPERTY_CALL = PARAM + "\\.";

   public static final String VARIABLE =
         "^[[A-Za-z][A-Za-z0-9]*|[0-9]+[fFlLmM]{0,1}]";
   public static final String PARAM_VARIABLE =
         "^[A-Za-z][A-Za-z0-9]* *[A-Za-z][A-Za-z0-9]*";
}
