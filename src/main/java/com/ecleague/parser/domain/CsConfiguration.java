package com.ecleague.parser.domain;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CsConfiguration {

   /**
    * List all needed compile cs files in order.
    * 
    * @return
    */
   List<CsSourceFile> listSourceFiles();

   /**
    * List all cs projects in order.
    * 
    * @return
    */
   List<CsProject> listProjects();
}
