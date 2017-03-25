package com.ecleague.parser.domain.impl;

import java.io.File;
import java.util.*;

import com.ecleague.parser.domain.CsConfiguration;
import com.ecleague.parser.domain.CsProject;
import com.ecleague.parser.domain.CsSourceFile;
import com.ecleague.parser.exception.ParseCsprojException;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public class CsConfigurationImpl implements CsConfiguration {
   /**
    * This domain will contain a dependency graph.
    */


   private Map<String, CsProject> csProjectMap = new HashMap<>();

   private List<CsProject> orderedProjects;

   /**
    * Init a cs configuration with a directory
    * 
    * @param directory
    */
   public CsConfigurationImpl(File directory) {
      if (directory == null)
         throw new NullPointerException("Target is Null");

      if (!directory.isDirectory())
         throw new IllegalArgumentException("Target is not a directory!");

      List<File> csFiles = goThroughDirectory(directory);

      for (File file : csFiles) {
         CsProjectImpl project = new CsProjectImpl(file);

         project.initBasicInfo();

         csProjectMap.put(project.getGuid(), project);
      }

      for (CsProject project : csProjectMap.values()) {
         project.initReferenceProject(csProjectMap);
      }

      sortProjects();
   }

   /**
    * Get all csproj files under the specified directory.
    * 
    * @param directory
    * @return
    */
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

   /**
    * According to the cs project dependencies relationship, sort all projects.
    */
   private void sortProjects() {
      Set<CsProject> projects = new HashSet<>(csProjectMap.values());

      orderedProjects = new ArrayList<>(projects.size());

      while (!projects.isEmpty()) {
         List<CsProject> tempProjects = new ArrayList<>();
         for (CsProject project : projects) {
            if (project.isRelyOnOtherProject())
               continue;

            tempProjects.add(project);
         }

         if (tempProjects.isEmpty())
            throw new ParseCsprojException("Project dependency exists problem");

         orderedProjects.addAll(tempProjects);
         for (CsProject project : projects) {
            project.removeDependenciesRely(tempProjects);
         }
         projects.removeAll(tempProjects);
      }
   }

   /**
    * List all needed compile cs files in order.
    *
    * @return
    */
   @Override
   public List<CsSourceFile> listSourceFiles() {
      List<CsSourceFile> files = new ArrayList<>();

      for (CsProject project : orderedProjects) {
         files.addAll(project.getCsSourceFiles());
      }

      return files;
   }

   /**
    * List all cs projects in order.
    *
    * @return
    */
   @Override
   public List<CsProject> listProjects() {
      return orderedProjects;
   }
}
