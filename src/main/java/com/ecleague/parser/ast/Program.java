package com.ecleague.parser.ast;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class Program implements SourceParser{
   private List<Clazz> clazzes;


   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public void parse(String sourceCode) {

   }
}
