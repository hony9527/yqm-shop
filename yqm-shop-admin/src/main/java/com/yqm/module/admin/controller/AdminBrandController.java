/*
 *  Copyright  2022 Wei xi mei
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.yqm.module.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqm.common.dto.YqmBrandDTO;
import com.yqm.common.request.YqmBrandRequest;
import com.yqm.common.response.ResponseBean;
import com.yqm.module.admin.service.AdminBrandService;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端-品牌
 *
 * @Author: weiximei
 * @Date: 2022/2/28 20:37
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
@RequestMapping("/api/admin/brand")
@RestController
public class AdminBrandController {

    private final AdminBrandService adminBrandService;

    public AdminBrandController(AdminBrandService adminBrandService) {
        this.adminBrandService = adminBrandService;
    }

    /**
     * 查询
     *
     * @param request
     * @return
     */
    @GetMapping("/list")
    public ResponseBean list(YqmBrandRequest request) {
        return ResponseBean.success(adminBrandService.list(request));
    }

    /**
     * 查询分页
     *
     * @param request
     * @return
     */
    @GetMapping("")
    public ResponseBean getPage(YqmBrandRequest request) {
        IPage page = adminBrandService.page(request);
        return ResponseBean.success(page);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseBean getById(@PathVariable String id) {
        YqmBrandDTO dto = adminBrandService.getById(id);
        return ResponseBean.success(dto);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseBean deleteById(@PathVariable String id) {
        String removeId = adminBrandService.deleteById(id);
        return ResponseBean.success(removeId);
    }

    /**
     * 保存
     *
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseBean save(@RequestBody YqmBrandRequest request) {
        YqmBrandDTO dto = adminBrandService.save(request);
        return ResponseBean.success(dto);
    }

    /**
     * 修改
     *
     * @param request
     * @return
     */
    @PutMapping("")
    public ResponseBean update(@RequestBody YqmBrandRequest request) {
        YqmBrandDTO dto = adminBrandService.save(request);
        return ResponseBean.success(dto);
    }

    /**
     * 是否显示
     *
     * @param request
     * @return
     */
    @PutMapping("/isShow")
    public ResponseBean isShow(@RequestBody YqmBrandRequest request) {
        YqmBrandDTO dto = adminBrandService.isShow(request);
        return ResponseBean.success(dto);
    }

    /**
     * 是否品牌制造商
     *
     * @param request
     * @return
     */
    @PutMapping("/isBrandManufacturers")
    public ResponseBean isBrandManufacturers(@RequestBody YqmBrandRequest request) {
        YqmBrandDTO dto = adminBrandService.isBrandManufacturers(request);
        return ResponseBean.success(dto);
    }
}
