/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn
 * 注意：
 * 本软件为www.yqmshop.cn开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.yqm.modules.user.rest;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.yqm.api.ApiResult;
import com.yqm.api.YqmShopException;
import com.yqm.common.bean.LocalUser;
import com.yqm.common.interceptor.AuthCheck;
import com.yqm.modules.user.service.YqmSystemUserLevelService;
import com.yqm.modules.user.service.YqmSystemUserTaskService;
import com.yqm.modules.user.service.YqmUserLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户等级 前端控制器
 * </p>
 *
 * @author weiximei
 * @since 2019-12-06
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "用户等级", tags = "用户:用户等级")
public class UserLevelController {

    private final YqmUserLevelService userLevelService;
    private final YqmSystemUserLevelService systemUserLevelService;
    private final YqmSystemUserTaskService systemUserTaskService;

    /**
    * 会员等级列表
    */
    @AuthCheck
    @GetMapping("/user/level/grade")
    @ApiOperation(value = "会员等级列表",notes = "会员等级列表")
    public ApiResult<Object> getLevelInfo(){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(systemUserLevelService.getLevelInfo(uid));
    }

    /**
     * 获取等级任务
     */
    @AuthCheck
    @GetMapping("/user/level/task/{id}")
    @ApiOperation(value = "获取等级任务",notes = "获取等级任务")
    public ApiResult<Object> getTask(@PathVariable String id){
        if(StrUtil.isBlank(id) || !NumberUtil.isNumber(id)){
            throw new YqmShopException("参数非法");
        }
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(systemUserTaskService.getTaskList(Integer.valueOf(id),uid));
    }

    /**
     * 检测用户是否可以成为会员
     */
    @AuthCheck
    @GetMapping("/user/level/detection")
    @ApiOperation(value = "检测用户是否可以成为会员",notes = "检测用户是否可以成为会员")
    public ApiResult<Object> detection(){
        Long uid = LocalUser.getUser().getUid();
        boolean res = userLevelService.setLevelComplete(uid);
        if(res){
            return ApiResult.ok("升级成功!");
        }else{
            throw new YqmShopException("还不符合升级条件哦!");
        }

    }



}

