/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn

 */
package com.yqm.modules.product.service.dto;

import com.yqm.annotation.Query;
import lombok.Data;

/**
* @author weiximei
* @date 2020-05-12
*/
@Data
public class YqmStoreProductReplyQueryCriteria{
    @Query
    private Integer isDel;
}
