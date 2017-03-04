package com.ecleague.parser.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public class CsConfiguration {
   /**
    * This domain will contain a dependency graph.
    */


   private Map<String, CsProject> csProjectMap = new HashMap<>();

   /**
    * Init a cs configuration with a directory
    * 
    * @param directory
    */
   public CsConfiguration(File directory) {
      if (directory == null)
         throw new NullPointerException("Target is Null");

      if (!directory.isDirectory())
         throw new IllegalArgumentException("Target is not a directory!");

      List<File> csFiles = goThroughDirectory(directory);

      for (File file : csFiles) {
         CsProject project = new CsProject(file);

         project.initBasicInfo();

         csProjectMap.put(project.getGuid(), project);
      }

      for (CsProject project : csProjectMap.values()) {
         project.initReferenceProject(csProjectMap);
      }
   }

   private List<File> goThroughDirectory(File directory) {
      List<File> files = new ArrayList<>();

      for (File file : directory.listFiles()) {
         if (file.isDirectory()) {
            files.addAll(goThroughDirectory(file));
            continue;
         }

         if (file.getName().endsWith("csproj")) {
            files.add(file);
         }
      }

      return files;
   }

}
