/*
 * Copyright 2021 Wei xi mei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *
 * distributed under the License is distributed on an "AS IS" BASIS,
 *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 *
 * limitations under the License.
 */

package com.yqm.common.request;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: weiximei
 * @Date: 2021/12/26 16:47
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@Data
public class CustomerSysConfigRequest extends BaseRequest {

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 描述
     */
    private String configDesc;

    /**
     * 排序
     */
    private String sort;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
