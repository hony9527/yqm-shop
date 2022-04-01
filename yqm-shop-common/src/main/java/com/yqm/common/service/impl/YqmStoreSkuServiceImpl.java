package com.yqm.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqm.common.entity.YqmProductSales;
import com.yqm.common.entity.YqmProductSkuInventory;
import com.yqm.common.entity.YqmStoreSku;
import com.yqm.common.mapper.YqmStoreSkuMapper;
import com.yqm.common.request.YqmStoreSkuRequest;
import com.yqm.common.service.IYqmStoreSkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku 服务实现类
 * </p>
 *
 * @author weiximei
 * @since 2022-01-30
 */
@Service
public class YqmStoreSkuServiceImpl extends ServiceImpl<YqmStoreSkuMapper, YqmStoreSku> implements IYqmStoreSkuService {

    private final YqmStoreSkuMapper yqmStoreSkuMapper;

    public YqmStoreSkuServiceImpl(YqmStoreSkuMapper yqmStoreSkuMapper) {
        this.yqmStoreSkuMapper = yqmStoreSkuMapper;
    }

    @Override
    public QueryWrapper<YqmStoreSku> getQuery(YqmStoreSkuRequest request) {
        QueryWrapper<YqmStoreSku> queryWrapper = new QueryWrapper<>();
        if (CollectionUtils.isNotEmpty(request.getInStatusList())) {
            queryWrapper.in("status", request.getInStatusList());
        }
        return queryWrapper;
    }

    @Override
    public List<YqmProductSkuInventory> getProductSkuInventory(List<String> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            return null;
        }
        return yqmStoreSkuMapper.getProductSkuInventory(productIdList);
    }

    @Override
    public List<YqmProductSales> getProductSales(List<String> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            return null;
        }
        return yqmStoreSkuMapper.getProductSales(productIdList);
    }
}
