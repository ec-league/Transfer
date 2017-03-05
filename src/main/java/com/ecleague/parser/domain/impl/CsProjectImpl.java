package com.ecleague.parser.domain.impl;

import java.io.File;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ecleague.parser.domain.CsProject;
import com.ecleague.parser.domain.CsSourceFile;
import com.ecleague.parser.exception.ParseCsprojException;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public class CsProjectImpl implements CsProject {
   private String guid;
   private String path;
   private String name;

   private Set<CsProject> dependProjects;

   private transient Set<CsProject> relyOnProjects;

   private List<CsSourceFile> csSourceFiles;

   public CsProjectImpl(File file) {
      path = file.getParent();
      name = file.getName();
   }

   @Override
   public void initBasicInfo() {
      Document document = getDocument();

      setGuid(document.getRootElement().element("PropertyGroup")
            .element("ProjectGuid").getText().toUpperCase());
      List itemGroups = document.getRootElement().elements("ItemGroup");

      csSourceFiles = new ArrayList<>();

      for (Object itemGroup : itemGroups) {
         Element item = (Element) itemGroup;

         List compiles = item.elements("Compile");

         if (compiles.isEmpty())
            continue;

         for (Object source : compiles) {
            CsSourceFile sourceFile = new CsSourceFile();

            String sourcePath = String.format("%s/%s", path,
                  ((Element) source).attributeValue("Include"));

            sourceFile.setPath(sourcePath);
            csSourceFiles.add(sourceFile);
         }
      }
   }

   @Override
   public void initReferenceProject(Map<String, CsProject> projectMap) {

      Document document = getDocument();

      dependProjects = new HashSet<>();
      relyOnProjects = new HashSet<>();
      List itemGroups = document.getRootElement().elements("ItemGroup");

      for (Object itemGroup : itemGroups) {
         Element item = (Element) itemGroup;


         List projectReference = item.elements("ProjectReference");

         if (projectReference.isEmpty())
            continue;

         for (Object project : projectReference) {

            String projectGuid = ((Element) project).elementText("Project").toUpperCase();

            CsProject project1 = projectMap.get(projectGuid);

            if (project1 == null)
               continue;

            relyOnProjects.add(project1);
            dependProjects.add(project1);
         }
      }
   }

   private Document getDocument() {
      File file = new File(String.format("%s/%s", path, name));
      SAXReader reader = new SAXReader();
      Document document;

      try {
         document = reader.read(file);
      } catch (DocumentException e) {
         throw new ParseCsprojException(
               "Parse Csproj Failed: " + file.getName(), e);
      }

      return document;
   }

   public String getGuid() {
      return guid;
   }

   public void setGuid(String guid) {
      this.guid = guid;
   }

   public Set<CsProject> getDependProjects() {
      return dependProjects;
   }

   public String getPath() {
      return path;
   }

   @Override
   public List<CsSourceFile> getCsSourceFiles() {
      return csSourceFiles;
   }

   /**
    * If current project rely on other project.
    *
    * @return
    */
   @Override
   public boolean isRelyOnOtherProject() {
      return relyOnProjects != null && !relyOnProjects.isEmpty();
   }

   /**
    * Remove all current project's dependencies rely on this.
    * @param csProjects
    */
   @Override
   public void removeDependenciesRely(List<CsProject> csProjects) {
      if (relyOnProjects == null || relyOnProjects.isEmpty())
         return;

      relyOnProjects.removeAll(csProjects);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (!(o instanceof CsProjectImpl))
         return false;

      CsProjectImpl csProject = (CsProjectImpl) o;

      if (!getGuid().equals(csProject.getGuid()))
         return false;
      if (!getPath().equals(csProject.getPath()))
         return false;
      if (!name.equals(csProject.name))
         return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = getGuid().hashCode();
      result = 31 * result + getPath().hashCode();
      result = 31 * result + name.hashCode();
      return result;
   }
}
