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

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqm.common.conversion.TpLinkClassifyToDTO;
import com.yqm.common.define.YqmDefine;
import com.yqm.common.dto.TpLinkClassifyDTO;
import com.yqm.common.entity.TpLinkClassify;
import com.yqm.common.request.TpLinkClassifyRequest;
import com.yqm.common.service.ITpLinkClassifyService;
import com.yqm.security.User;
import com.yqm.security.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 管理端-友情链接分类
 * @Author: weiximei
 * @Date: 2021/11/7 17:30
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@Service
@Slf4j
public class LinkClassifyService {

    private ITpLinkClassifyService iTpLinkClassifyService;

    public LinkClassifyService(ITpLinkClassifyService iTpLinkClassifyService) {
        this.iTpLinkClassifyService = iTpLinkClassifyService;
    }

    /**
     * 保存/修改 友情链接分类
     * @param request
     * @return
     */
    public TpLinkClassifyDTO saveLinkClassify(TpLinkClassifyRequest request) {
        User user = UserInfoService.getUser();

        TpLinkClassify linkClassify = TpLinkClassifyToDTO.toTpLinkClassify(request);
        if (StringUtils.isEmpty(request.getId())) {
            linkClassify.setCreatedBy(user.getId());
            linkClassify.setCreatedTime(LocalDateTime.now());
        }

        linkClassify.setUserId(user.getId());
        linkClassify.setStatus(YqmDefine.StatusType.effective.getValue());
        linkClassify.setUpdatedBy(user.getId());
        linkClassify.setUpdatedTime(LocalDateTime.now());
        iTpLinkClassifyService.saveOrUpdate(linkClassify);

        return TpLinkClassifyToDTO.toTpLinkClassifyDTO(linkClassify);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TpLinkClassifyDTO getById(String id) {
        TpLinkClassify linkClassify = iTpLinkClassifyService.getById(id);
        return TpLinkClassifyToDTO.toTpLinkClassifyDTO(linkClassify);
    }

    /**
     * 删除友情链接分类
     * @param id
     * @return
     */
    public String removeLinkClassify(String id) {
        User user = UserInfoService.getUser();

        UpdateWrapper<TpLinkClassify> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", YqmDefine.StatusType.delete.getValue());
        updateWrapper.eq("id", id);
        updateWrapper.eq("user_id", user.getId());
        iTpLinkClassifyService.update(updateWrapper);

        return id;
    }

    /**
     * 停用/启用
     * @return
     */
    public String enableLinkClassify(TpLinkClassifyRequest request) {
        User user = UserInfoService.getUser();

        if (!YqmDefine.includeStatus.contains(request.getStatus())) {
            log.error("操作异常->停用/启用友情链接分类错误->传入状态不正确！[id={},status={}]", request.getId(), request.getStatus());
            return request.getId();
        }

        TpLinkClassify linkClassify = iTpLinkClassifyService.getById(request.getId());
        if (Objects.isNull(linkClassify)) {
            log.error("操作异常->停用/启用友情链接分类错误->数据未找到！[id={}]", request.getId());
            return request.getId();
        }
        if (YqmDefine.StatusType.delete.getValue().equals(linkClassify.getStatus())) {
            log.error("操作异常->停用/启用友情链接分类错误->该信息已经被删除！[id={}]", request.getId());
            return request.getId();
        }

        UpdateWrapper<TpLinkClassify> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", request.getStatus());
        updateWrapper.eq("id", request.getId());
        updateWrapper.eq("user_id", user.getId());
        iTpLinkClassifyService.update(updateWrapper);

        return request.getId();

    }

    /**
     * 分页查询 友情链接分类
     * @param request
     * @return
     */
    public IPage<TpLinkClassifyDTO> pageLinkClassify(TpLinkClassifyRequest request) {
        Page<TpLinkClassify> page = new Page<>();
        page.setCurrent(request.getCurrent());
        page.setSize(request.getPageSize());

        request.setIncludeStatus(Arrays.asList(YqmDefine.StatusType.effective.getValue(), YqmDefine.StatusType.failure.getValue()));
        IPage pageList = iTpLinkClassifyService.page(page, iTpLinkClassifyService.queryWrapper(request));

        List list = pageList.getRecords();
        if (CollectionUtils.isNotEmpty(list)) {
            pageList.setRecords(TpLinkClassifyToDTO.toTpLinkClassifyDTOList(list));
        }
        return pageList;
    }

    /**
     * 查询 友情链接分类
     * @param request
     * @return
     */
    public List<TpLinkClassifyDTO> listLinkClassify(TpLinkClassifyRequest request) {
        List<TpLinkClassifyDTO> linkClassifyDTOS = new ArrayList<>();

        request.setIncludeStatus(Arrays.asList(YqmDefine.StatusType.effective.getValue(), YqmDefine.StatusType.failure.getValue()));
        List<TpLinkClassify> classifyList = iTpLinkClassifyService.list(iTpLinkClassifyService.queryWrapper(request));
        if (CollectionUtils.isNotEmpty(classifyList)) {
            linkClassifyDTOS = TpLinkClassifyToDTO.toTpLinkClassifyDTOList(classifyList);
        }
        return linkClassifyDTOS;
    }

}
