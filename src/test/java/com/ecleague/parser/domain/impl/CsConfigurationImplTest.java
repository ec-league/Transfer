package com.ecleague.parser.domain.impl;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ecleague.parser.domain.CsConfiguration;
import com.ecleague.parser.domain.CsProject;

/**
 * CsConfigurationImpl Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class CsConfigurationImplTest {
   /**
    * 
    * Method: listSourceFiles()
    * 
    */
   @Test
   public void testListSourceFiles() throws Exception {
      File directory = new File(this.getClass().getResource("/").getPath());

      CsConfiguration configuration = new CsConfigurationImpl(directory);

      Assert.assertNotNull(configuration);

      Assert.assertTrue(configuration.listSourceFiles().size() > 0);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructor1() {
      File directory = new File(this.getClass().getResource("/").getPath()
            + "/BussinessManager.csproj");

      CsConfiguration configuration = new CsConfigurationImpl(directory);
   }

   @Test(expected = NullPointerException.class)
   public void testConstructor2() {
      File directory = null;

      CsConfiguration configuration = new CsConfigurationImpl(directory);
   }

   /**
    * 
    * Method: listProjects()
    * 
    */
   @Test
   public void testListProjects() throws Exception {
      File directory = new File(this.getClass().getResource("/").getPath());

      CsConfiguration configuration = new CsConfigurationImpl(directory);

      Assert.assertNotNull(configuration);

      List<CsProject> projectList = configuration.listProjects();

      Assert.assertTrue(projectList.size() == 15);
   }
}
