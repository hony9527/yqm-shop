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

package com.yqm.module.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqm.common.dto.TpSiteDTO;
import com.yqm.common.request.TpSiteRequest;
import com.yqm.common.response.ResponseBean;
import com.yqm.module.admin.service.SiteService;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端-站点
 *
 * @Author: weiximei
 * @Date: 2021/11/7 19:09
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@RequestMapping("/admin/site")
@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    /**
     * 添加站点
     *
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseBean addRecruitment(@RequestBody TpSiteRequest request) {
        TpSiteDTO dto = siteService.saveSite(request);
        return ResponseBean.success(dto);
    }

    /**
     * 修改站点
     *
     * @param request
     * @return
     */
    @PutMapping("")
    public ResponseBean updateRecruitment(@RequestBody TpSiteRequest request) {
        TpSiteDTO dto = siteService.saveSite(request);
        return ResponseBean.success(dto);
    }

    /**
     * 删除站点
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseBean removeSite(@PathVariable("id") String id) {
        String removeId = siteService.removeSite(id);
        return ResponseBean.success(removeId);
    }

    /**
     * 根据id查询站点
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseBean getById(@PathVariable("id") String id) {
        TpSiteDTO dto = siteService.getById(id);
        return ResponseBean.success(dto);
    }


    /**
     * 分页查询站点
     *
     * @param request
     * @return
     */
    @GetMapping("/page")
    public ResponseBean pageRecruitment(TpSiteRequest request) {
        IPage<TpSiteDTO> page = siteService.pageSite(request);
        return ResponseBean.success(page);
    }

    /**
     * 停用/启用 站点
     *
     * @param request
     * @return
     */
    @PutMapping("/enable")
    public ResponseBean enableRecruitment(@RequestBody TpSiteRequest request) {
        Long enableId = siteService.enableSite(request);
        return ResponseBean.success(enableId);
    }

    /**
     * 置顶 站点
     *
     * @param request
     * @return
     */
    @PutMapping("/top")
    public ResponseBean top(@RequestBody TpSiteRequest request) {
        Long enableId = siteService.top(request.getId());
        return ResponseBean.success(enableId);
    }


}
