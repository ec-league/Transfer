package com.ecleague.parser.domain;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

/**
 * CsProject Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class CsProjectTest {

   @Test
   public void testInitBasicInfo() {
      File file = new File(this.getClass().getResource("/").getPath()
            + "csdemo/XProductSearch.csproj");

      CsProject project = new CsProject(file);

      project.initBasicInfo();

      Assert.assertTrue(
            project.getGuid().equals("{57AE68FC-709A-4AF6-B0C1-944516BE128D}"));

      Assert.assertEquals(project.getCsSourceFiles().size(), 9);

      Assert.assertEquals(project.getCsSourceFiles().get(0).getPath(),
            file.getParent() + "/CacheTest.aspx.cs");
   }
}
