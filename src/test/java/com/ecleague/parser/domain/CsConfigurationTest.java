package com.ecleague.parser.domain;

import java.io.File;

import org.junit.Test;

/**
 * CsConfiguration Tester.
 * 
 * @author EthanPark
 * @version 1.0
 */
public class CsConfigurationTest {


   /**
    * 
    * Method: goThroughDirectory(File directory)
    * 
    */
   @Test
   public void testConstructor() throws Exception {
      File directory = new File(this.getClass().getResource("/").getPath());

      CsConfiguration configuration = new CsConfiguration(directory);

   }

}
