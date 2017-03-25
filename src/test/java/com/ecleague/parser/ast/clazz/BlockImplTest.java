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
   public void testParseFunction() throws Exception {
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
      Assert.assertEquals(block1.getReturnType(), "bool");
   }

   @Test
   public void testParseField(){

      String sourceCode = "private static readonly IAgentCache AgentCache = CacheFactory.GetCache();";

      BlockImpl block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode), "");

      Assert.assertEquals(block.getQualifier(), "private");
      Assert.assertTrue(block.isStaticBlock());
      Assert.assertTrue(block.isReadonlyBlock());

      Assert.assertEquals(block.getType(), "IAgentCache");

      FieldBlock block1 = (FieldBlock) block.getSubBlock();

      Assert.assertEquals(block1.getFiledName(), "AgentCache");
      Assert.assertEquals(block1.getReturnType(), "IAgentCache");
      Assert.assertNotNull(block1.getInitValue());
   }
}
