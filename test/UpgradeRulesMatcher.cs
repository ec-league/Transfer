using System;
using System.Collections.Generic;
using System.Linq;
using Ctrip.IntlFlight.XProductSearch.CacheManager;
using Ctrip.IntlFlight.XProductSearch.DomainModel;
using Utilities.Enums;

namespace Ctrip.
IntlFlight.XProductSearch.BussinessManager.Upgrade
{
    public class UpgradeRulesMatcher : IUpgradeRulesMatcher
    {
        private static readonly ICityAreaCache AreaCache = CacheFactory.GetCache<ICityAreaCache>();
        private static readonly IUpgradeProductCache UpgradeProductCache = CacheFactory.GetCache<IUpgradeProductCache>();
        private static readonly IAgentCache AgentCache = CacheFactory.GetCache<IAgentCache>();

        /// <summary>
        /// 根据查询条件查找匹配的升舱产品，当不存在时，返回false，存在时返回true
        /// </summary>
        /// <param name="entity"></param>
        /// <param name="products"></param>
        /// <returns></returns>
        public bool TryGetUpgradeProduct(UpgradeMatchEntity entity, out List<UpgradeProduct> products)
        {
            var upgradeProducts = UpgradeProductCache.GetProducts();

            var result = new List<UpgradeProduct>();

            foreach (var entry in upgradeProducts)
            {
                foreach (var upgradeProduct in entry.Value)
                {
                    if (!IsProductMatch(upgradeProduct, entity))
                        continue;

                    result.Add(upgradeProduct);
                    break;
                }
            }

            products = result;

            if (result.Count == 0)
                return false;
            return true;
        }

        public bool TryGetUpgradeProductById(long id, out UpgradeProduct product)
        {
            product = UpgradeProductCache.GetUpgradeProductById(id);

            if (product == null)
                return false;

            return true;
        }

        private bool IsProductMatch(UpgradeProduct product, UpgradeMatchEntity entity)
        {
            if (!IsAgentMatch(product, entity.AgentId))
                return false;

            if (!product.MatchTripType(entity.TripType))
                return false;

            if (!product.MatchValidatingCarrier(entity.ValidatingCarrier))
                return false;

            if (!product.MatchFlightNo(entity.DepartMainFlightNo))
                return false;

            if (product.DAreas.Count != 0 && !IsAreaMatch(product.DAreas, entity.Origin))
                return false;

            if (product.AAreas.Count != 0 && !IsAreaMatch(product.AAreas, entity.FirstArrvialCity))
                return false;

            if (!product.MatchToRanges(entity.DepartDate))
                return false;

            if (entity.TripType == TripType.RT && !product.MatchBackRanges(entity.ArravialDate))
                return false;

            if (!product.MatchSalesDate)
                return false;

            if (!product.MatchSeatClass(entity.DepartMainSegment.SeatClass))
                return false;

            if (!product.MatchSeatGrade(entity.DepartMainSegment.SeatGrade))
                return false;

            return true;
        }

        private bool IsAreaMatch(HashSet<string> areas, string city)
        {
            return areas.Any(area => AreaCache.IsCityInArea(area, city));
        }

        /// <summary>
        /// 如果产品Id为0，匹配所有自有票台
        /// </summary>
        /// <param name="product"></param>
        /// <param name="agentId"></param>
        /// <returns></returns>
        private bool IsAgentMatch(UpgradeProduct product, int agentId)
        {
            if (product.AgencyId == 0)
                return AgentCache.IsOwnAgency(agentId);

            return product.AgencyId == agentId;
        }
    }
}
