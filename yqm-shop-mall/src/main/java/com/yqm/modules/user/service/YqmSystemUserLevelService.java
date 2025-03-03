/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn
 * 注意：
 * 本软件为www.yqmshop.cn开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.yqm.modules.user.service;

import com.yqm.common.service.BaseService;
import com.yqm.modules.shop.domain.YqmSystemUserLevel;
import com.yqm.modules.user.service.dto.UserLevelDto;
import com.yqm.modules.user.service.dto.YqmSystemUserLevelDto;
import com.yqm.modules.user.service.dto.YqmSystemUserLevelQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author weiximei
* @date 2020-05-12
*/
public interface YqmSystemUserLevelService  extends BaseService<YqmSystemUserLevel>{


    /**
     * 获取当前的下一个会员等级id
     * @param levelId 等级id
     * @return int
     */
    int getNextLevelId(int levelId);

    //boolean getClear(int levelId);


    /**
     * 获取会员等级列表及其任务列表
     * @return UserLevelDto
     */
    UserLevelDto getLevelInfo(Long uid);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YqmSystemUserLevelQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YqmSystemUserLevelDto>
    */
    List<YqmSystemUserLevel> queryAll(YqmSystemUserLevelQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YqmSystemUserLevelDto> all, HttpServletResponse response) throws IOException;
}
