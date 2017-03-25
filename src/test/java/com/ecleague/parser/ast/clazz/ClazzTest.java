package com.ecleague.parser.ast.clazz;

import com.ecleague.parser.ast.util.PreFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yun.li on 2017/3/25.
 */
public class ClazzTest {
   @Test
   public void parseClazz() throws Exception {
      String sourceCode = "using System;\n"
            + "using System.Collections.Generic;\n" + "using System.Linq;\n"
            + "using Ctrip.IntlFlight.XProductSearch.CacheManager;\n"
            + "using Ctrip.IntlFlight.XProductSearch.DomainModel;\n"
            + "using Utilities.Enums;\n" + "\n" + "namespace Ctrip.\n"
            + "IntlFlight.XProductSearch.BussinessManager.Upgrade\n" + "{\n"
            + "    public class UpgradeRulesMatcher : IUpgradeRulesMatcher\n"
            + "    {\n"
            + "        private static readonly ICityAreaCache AreaCache = CacheFactory.GetCache();\n"
            + "        private static readonly IUpgradeProductCache UpgradeProductCache = CacheFactory.GetCache();\n"
            + "        private static readonly IAgentCache AgentCache = CacheFactory.GetCache();\n"
            + "\n" + "        /// <summary>\n"
            + "        /// 根据查询条件查找匹配的升舱产品，当不存在时，返回false，存在时返回true\n"
            + "        /// </summary>\n"
            + "        /// <param name=\"entity\"></param>\n"
            + "        /// <param name=\"products\"></param>\n"
            + "        /// <returns></returns>\n"
            + "        public bool TryGetUpgradeProduct(UpgradeMatchEntity entity, out List products)\n"
            + "        {\n"
            + "            var upgradeProducts = UpgradeProductCache.GetProducts();\n"
            + "\n" + "            var result = new List();\n"
            + "\n" + "            foreach (var entry in upgradeProducts)\n"
            + "            {\n"
            + "                foreach (var upgradeProduct in entry.Value)\n"
            + "                {\n"
            + "                    if (!IsProductMatch(upgradeProduct, entity))\n"
            + "                        continue;\n" + "\n"
            + "                    result.Add(upgradeProduct);\n"
            + "                    break;\n" + "                }\n"
            + "            }\n" + "\n" + "            products = result;\n"
            + "\n" + "            if (result.Count == 0)\n"
            + "                return false;\n" + "            return true;\n"
            + "        }\n" + "\n"
            + "        public bool TryGetUpgradeProductById(long id, out UpgradeProduct product)\n"
            + "        {\n"
            + "            product = UpgradeProductCache.GetUpgradeProductById(id);\n"
            + "\n" + "            if (product == null)\n"
            + "                return false;\n" + "\n"
            + "            return true;\n" + "        }\n" + "\n"
            + "        private bool IsProductMatch(UpgradeProduct product, UpgradeMatchEntity entity)\n"
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
            + "            return true;\n" + "        }\n" + "\n"
            + "        private bool IsAreaMatch(HashSet areas, string city)\n"
            + "        {\n"
            + "            return false;\n"
            + "        }\n" + "\n" + "        /// <summary>\n"
            + "        /// 如果产品Id为0，匹配所有自有票台\n" + "        /// </summary>\n"
            + "        /// <param name=\"product\"></param>\n"
            + "        /// <param name=\"agentId\"></param>\n"
            + "        /// <returns></returns>\n"
            + "        private bool IsAgentMatch(UpgradeProduct product, int agentId)\n"
            + "        {\n" + "            if (product.AgencyId == 0)\n"
            + "                return AgentCache.IsOwnAgency(agentId);\n" + "\n"
            + "            return product.AgencyId == agentId;\n"
            + "        }\n" + "    }\n" + "}\n";

      sourceCode = PreFormat.removeUnusedInfo(sourceCode);

      Clazz clazz = new Clazz();
      Assert.assertEquals(clazz.parse(sourceCode), "");
   }
}