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

package com.yqm.module.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.yqm.common.conversion.TpCompanyToDTO;
import com.yqm.common.dto.TpCompanyDTO;
import com.yqm.common.entity.TpCompany;
import com.yqm.common.exception.YqmException;
import com.yqm.common.request.TpCompanyRequest;
import com.yqm.common.service.ITpCompanyService;
import com.yqm.security.User;
import com.yqm.security.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 管理端-公司
 *
 * @Author: weiximei
 * @Date: 2021/10/24 19:08
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@Slf4j
@Service
public class CompanyService {

    @Autowired
    private ITpCompanyService iTpCompanyService;

    /**
     * 检查是否存在 公司
     * @return
     */
    private boolean checkUserBindingCompany() {
        TpCompany company = iTpCompanyService.getByUserId(UserInfoService.getUser().getId());
        return Objects.nonNull(company);
    }

    /**
     * 检查公司 是否 在自己名下
     * @return
     */
    private boolean checkUserByCompany(String companyId) {
        String currentUserId = UserInfoService.getUser().getId();
        TpCompany company = iTpCompanyService.getById(companyId);
        if (null != company && StringUtils.equals(currentUserId, company.getUserId())) {
            return true;
        }
        return false;
    }

    /**
     * 添加公司
     * @param request
     * @return
     */
    public TpCompanyDTO addCompany(TpCompanyRequest request) {
        User currentUser = UserInfoService.getUser();

        if (this.checkUserBindingCompany()) {
            log.error("异常 -> 用户添加公司操作-用户已存在公司，无法添加![userId={}]", currentUser.getId());
            throw new YqmException("数据异常!");
        }


        TpCompany company = TpCompanyToDTO.toTpCompany(request);
        company.setUserId(currentUser.getId());
        company.setCreatedTime(LocalDateTimeUtil.now());
        company.setCreatedBy(currentUser.getId());
        company.setUpdatedTime(LocalDateTime.now());
        company.setUpdatedBy(currentUser.getId());
        if (!iTpCompanyService.save(company)) {
            throw new YqmException("操作失败!");
        }

        return TpCompanyToDTO.toTpCompanyDTO(company);
    }

    /**
     * 修改公司
     * @param request
     * @return
     */
    public TpCompanyDTO updateCompany(TpCompanyRequest request) {

        User currentUser = UserInfoService.getUser();
        if (!this.checkUserByCompany(request.getId())) {
            log.error("异常 -> 用户修改公司操作-公司不在这个用户名下![companyId={}, userId={}]", request.getId(), currentUser.getId());
            throw new YqmException("数据异常!");
        }


        TpCompany newCompany = TpCompanyToDTO.toTpCompany(request);
        TpCompany oldCompany = iTpCompanyService.getById(request.getId());

        BeanUtil.copyProperties(newCompany, oldCompany);

        oldCompany.setUpdatedTime(LocalDateTime.now());
        oldCompany.setUpdatedBy(currentUser.getId());
        if (!iTpCompanyService.updateById(oldCompany)) {
            throw new YqmException("操作失败!");
        }

        return TpCompanyToDTO.toTpCompanyDTO(oldCompany);
    }

}
