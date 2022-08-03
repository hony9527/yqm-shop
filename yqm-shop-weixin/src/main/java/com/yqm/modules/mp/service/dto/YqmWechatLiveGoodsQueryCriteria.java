/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yqmshop.cn
* 注意：
* 本软件为www.yqmshop.cn开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.yqm.modules.mp.service.dto;

import com.yqm.annotation.Query;
import lombok.Data;

/**
* @author weiximei
* @date 2020-08-11
*/
@Data
public class YqmWechatLiveGoodsQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}
