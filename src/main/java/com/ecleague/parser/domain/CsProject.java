package com.ecleague.parser.domain;

import java.util.List;
import java.util.Map;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CsProject {

   /**
    * Rely on an project.
    * 
    * @param project
    */
   void relyOnProject(CsProject project);

   /**
    * Init all the cs project dependencies.
    * 
    * @param projectMap
    */
   void initReferenceProject(Map<String, CsProject> projectMap);

   /**
    * Init all basic information like guid and path.
    */
   void initBasicInfo();

   /**
    * Get all project included cs source files.
    * 
    * @return
    */
   List<CsSourceFile> getCsSourceFiles();

   /**
    * If current project rely on other project.
    * 
    * @return
    */
   boolean isRelyOnOtherProject();

   /**
    * Remove all current project's dependencies rely on this.
    * 
    * @param csProjects
    */
   void removeDependenciesRely(List<CsProject> csProjects);
}
