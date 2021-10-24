package com.yqm.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqm.common.entity.TpRegion;
import com.yqm.common.request.TpRegionRequest;

import java.util.List;

/**
* <p>
    * 地区表 服务类
    * </p>
*
* @author weiximei
* @since 2021-10-24
*/
public interface ITpRegionService extends IService<TpRegion> {

    QueryWrapper<TpRegion> queryWrapper(TpRegionRequest request);

    /**
     * 查询省市区
     * @param pCode
     * @return
     */
    List<TpRegion> getProvinces(Integer pCode);

}
