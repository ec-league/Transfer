package com.ecleague.parser;

import com.ecleague.parser.domain.impl.CsConfigurationImpl;

import java.io.File;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CsprojParser {
   /**
    * This interface mainly deal with the csproj file. To know which project
    * rely on which project. Developers should maintain a graph to deal with the
    * dependency graph.
    */

   CsConfigurationImpl loadFromFile(File directory);
}
