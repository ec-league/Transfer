package com.ecleague.parser.domain;

import java.io.File;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ecleague.parser.exception.ParseCsprojException;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/4<br/>
 * Email: byp5303628@hotmail.com
 */
public class CsProject {
   private String guid;
   private String path;
   private String name;

   private Set<CsProject> dependProjects;

   private Set<CsProject> relyOnProjects;

   private List<CsSourceFile> csSourceFiles;

   public CsProject() {

   }

   public CsProject(File file) {
      path = file.getParent();
      name = file.getName();
   }

   public void initBasicInfo() {
      Document document = getDocument();

      setGuid(document.getRootElement().element("PropertyGroup")
            .element("ProjectGuid").getText());
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

   public void initReferenceProject(Map<String, CsProject> projectMap) {

      Document document = getDocument();

      dependProjects = new HashSet<>();
      List itemGroups = document.getRootElement().elements("ItemGroup");

      for (Object itemGroup : itemGroups) {
         Element item = (Element) itemGroup;


         List projectReference = item.elements("ProjectReference");

         if (projectReference.isEmpty())
            continue;

         for (Object project : projectReference) {

            String projectGuid = ((Element) project).elementText("Project");

            CsProject project1 = projectMap.get(projectGuid);

            if (project1 == null)
               continue;

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

   public void setDependProjects(Set<CsProject> dependProjects) {
      this.dependProjects = dependProjects;
   }

   public Set<CsProject> getRelyOnProjects() {
      return relyOnProjects;
   }

   public void setRelyOnProjects(Set<CsProject> relyOnProjects) {
      this.relyOnProjects = relyOnProjects;
   }

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public List<CsSourceFile> getCsSourceFiles() {
      return csSourceFiles;
   }

   public void setCsSourceFiles(List<CsSourceFile> csSourceFiles) {
      this.csSourceFiles = csSourceFiles;
   }
}
