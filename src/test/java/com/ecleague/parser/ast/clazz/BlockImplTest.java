package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.util.PreFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * BlockImpl Tester.
 *
 * @author EthanPark
 * @version 1.0
 * @since
 * 
 *        <pre>
 * 三月 25, 2017
 *        </pre>
 */
public class BlockImplTest {

   /**
    * Method: parse(String sourceCode)
    */
   @Test
   public void testParseFunction() throws Exception {
      String sourceCode =
            "public bool TryGetUpgradeProductById(long id, out UpgradeProduct product)"
                  + "        {"
                  + "            product = UpgradeProductCache.GetUpgradeProductById(id);"
                  + "" + "" + "            return true;" + "        }";

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
   public void testParseField() {

      String sourceCode =
            "private static readonly IAgentCache AgentCache = CacheFactory.GetCache();";

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

   @Test
   public void testParseProperty() {
      String sourceCode = "public Abc Abc {get;set;}";

      BlockImpl block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode), "");

      Assert.assertTrue(block.getSubBlock() instanceof PropertyBlock);

      PropertyBlock propertyBlock = (PropertyBlock) block.getSubBlock();

      Assert.assertEquals(propertyBlock.getPropertyName(), "Abc");
      Assert.assertEquals(propertyBlock.getReturnType(), "Abc");

      Assert.assertEquals(propertyBlock.getGetQualifier(), "public");
      Assert.assertEquals(propertyBlock.getSetQualifier(), "public");

      sourceCode = "public int Abc {get {return 5;} protected set;}";

      block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode), "");

      Assert.assertTrue(block.getSubBlock() instanceof PropertyBlock);

      propertyBlock = (PropertyBlock) block.getSubBlock();

      Assert.assertEquals(propertyBlock.getPropertyName(), "Abc");
      Assert.assertEquals(propertyBlock.getReturnType(), "int");

      Assert.assertEquals(propertyBlock.getGetQualifier(), "public");
      Assert.assertEquals(propertyBlock.getSetQualifier(), "protected");

      Assert.assertEquals(propertyBlock.getGetStatements().size(), 1);
   }

   @Test
   public void testFieldBlock() {
      String sourceCode =
            "private static readonly ICityAreaCache AreaCache = CacheFactory.GetCache();\n"
                  + "        private static readonly IUpgradeProductCache UpgradeProductCache = CacheFactory.GetCache();";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);

      BlockImpl block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode),
            "private static readonly IUpgradeProductCache UpgradeProductCache = CacheFactory.GetCache();");
   }

   @Test
   public void testFunctionBlock() {
      String sourceCode =
            "public bool TryGetUpgradeProduct(UpgradeMatchEntity entity, out List products)\n"
                  + "        {\n"
                  + "            var upgradeProducts = UpgradeProductCache.GetProducts();\n"
                  + "\n" + "            var result = new List();\n" + "\n"
                  + "            foreach (var entry in upgradeProducts)\n"
                  + "            {\n"
                  + "                foreach (var upgradeProduct in entry.Value)\n"
                  + "                {\n"
                  + "                    if (!IsProductMatch(upgradeProduct, entity))\n"
                  + "                        continue;\n" + "\n"
                  + "                    result.Add(upgradeProduct);\n"
                  + "                    break;\n" + "                }\n"
                  + "            }\n" + "\n"
                  + "            products = result;\n" + "\n"
                  + "            if (result.Count == 0)\n"
                  + "                return false;\n"
                  + "            return true;\n" + "        }";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);
      BlockImpl block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode), "");
   }

   @Test
   public void testFunctionBlock1() {
      String sourceCode =
            "  private bool IsProductMatch(UpgradeProduct product, UpgradeMatchEntity entity)\n"
                  + "        {\n"
                  + "            if (!IsAgentMatch(product, entity.AgentId))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchTripType(entity.TripType))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchValidatingCarrier(entity.ValidatingCarrier))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchFlightNo(entity.DepartMainFlightNo))\n"
                  + "                return false;\n" + "\n"
                  + "            if (product.DAreas.Count != 0 && !IsAreaMatch(product.DAreas, entity.Origin))\n"
                  + "                return false;\n" + "\n"
                  + "            if (product.AAreas.Count != 0 && !IsAreaMatch(product.AAreas, entity.FirstArrvialCity))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchToRanges(entity.DepartDate))\n"
                  + "                return false;\n" + "\n"
                  + "            if (entity.TripType == TripType.RT && !product.MatchBackRanges(entity.ArravialDate))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchSalesDate)\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchSeatClass(entity.DepartMainSegment.SeatClass))\n"
                  + "                return false;\n" + "\n"
                  + "            if (!product.MatchSeatGrade(entity.DepartMainSegment.SeatGrade))\n"
                  + "                return false;\n" + "\n"
                  + "            return true;\n" + "        }";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);

      BlockImpl block = new BlockImpl();

      Assert.assertEquals(block.parse(sourceCode), "");
   }
}
