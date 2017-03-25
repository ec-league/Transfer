package com.ecleague.parser.ast.clazz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BlockImpl Tester.
 *
 * @author EthanPark
 * @version 1.0
 * @since <pre>三月 25, 2017</pre>
 */
public class BlockImplTest {

   /**
    * Method: parse(String sourceCode)
    */
   @Test
   public void testParse() throws Exception {
//TODO: Test goes here...
      String sourceCode = "public bool TryGetUpgradeProductById(long id, out UpgradeProduct product)" +
            "        {" +
            "            product = UpgradeProductCache.GetUpgradeProductById(id);" +
            "" +
            "" +
            "            return true;" +
            "        }";

      BlockImpl block = new BlockImpl();
      Assert.assertEquals(block.parse(sourceCode), "");

      Assert.assertEquals(block.getQualifier(), "public");
      Assert.assertEquals(block.getType(), "bool");

      FunctionBlock block1 = (FunctionBlock) block.getSubBlock();

      Assert.assertEquals(block1.getSignature(), "TryGetUpgradeProductById");
      Assert.assertEquals(block1.getInnerStatements().size(), 2);

      Assert.assertEquals(block1.getParamTypeList().size(), 2);

      Assert.assertTrue(block1.getParamTypeList().get(1).isOut());
   }
}
