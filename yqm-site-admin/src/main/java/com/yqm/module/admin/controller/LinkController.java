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
import com.yqm.common.dto.TpLinkDTO;
import com.yqm.common.request.TpLinkRequest;
import com.yqm.common.request.TpRecruitmentRequest;
import com.yqm.common.response.ResponseBean;
import com.yqm.module.admin.service.LinkService;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端-友情链接
 * @Author: weiximei
 * @Date: 2021/11/7 19:09
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@RequestMapping("/admin/link")
@RestController
public class LinkController {

    private final LinkService linkClassifyService;

    public LinkController(LinkService linkClassifyService) {
        this.linkClassifyService = linkClassifyService;
    }

    /**
     * 添加友情链接分类
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseBean addRecruitment(@RequestBody TpLinkRequest request) {
        TpLinkDTO dto = linkClassifyService.saveLink(request);
        return ResponseBean.success(dto);
    }

    /**
     * 修改友情链接分类
     * @param request
     * @return
     */
    @PutMapping("")
    public ResponseBean updateRecruitment(@RequestBody TpLinkRequest request) {
        TpLinkDTO dto = linkClassifyService.saveLink(request);
        return ResponseBean.success(dto);
    }

    /**
     * 删除友情链接分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseBean removeLink(@PathVariable("id") String  id) {
        String removeId = linkClassifyService.removeLink(id);
        return ResponseBean.success(removeId);
    }

    /**
     * 根据id查询友情链接分类
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseBean getById(@PathVariable("id") String  id) {
        TpLinkDTO dto = linkClassifyService.getById(id);
        return ResponseBean.success(dto);
    }


    /**
     * 分页查询友情链接分类
     * @param request
     * @return
     */
    @GetMapping("/page")
    public ResponseBean pageRecruitment(TpLinkRequest request) {
        IPage<TpLinkDTO> page = linkClassifyService.pageLink(request);
        return ResponseBean.success(page);
    }

    /**
     * 停用/启用 友情链接分类
     * @param request
     * @return
     */
    @PutMapping("/enable")
    public ResponseBean enableRecruitment(@RequestBody TpLinkRequest request) {
        String enableId = linkClassifyService.enableLink(request);
        return ResponseBean.success(enableId);
    }

    /**
     * 置顶 友情链接
     * @param request
     * @return
     */
    @PutMapping("/top")
    public ResponseBean top(@RequestBody TpLinkRequest request) {
        String enableId = linkClassifyService.top(request.getId());
        return ResponseBean.success(enableId);
    }


}
