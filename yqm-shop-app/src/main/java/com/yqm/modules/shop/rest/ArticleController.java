/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn
 * 注意：
 * 本软件为www.yqmshop.cn开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.yqm.modules.shop.rest;

import com.yqm.api.ApiResult;
import com.yqm.modules.mp.service.YqmArticleService;
import com.yqm.modules.mp.vo.YqmArticleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author weiximei
 * @since 2019-10-02
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/article")
@Api(value = "文章模块", tags = "商城:文章模块")
public class ArticleController {

    private final YqmArticleService articleService;


    /**
    * 获取文章文章详情
    */
    @GetMapping("/details/{id}")
    @ApiOperation(value = "文章详情",notes = "文章详情")
    public ApiResult<YqmArticleQueryVo> getYqmArticle(@PathVariable Integer id){
        YqmArticleQueryVo yqmArticleQueryVo = articleService.getDetail(id);
        articleService.incVisitNum(id);
        return ApiResult.ok(yqmArticleQueryVo);
    }

    /**
     * 文章列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "文章列表",notes = "文章列表")
    public ApiResult<List<YqmArticleQueryVo>> getYqmArticlePageList(@RequestParam(value = "page",defaultValue = "1") int page,
                                                                  @RequestParam(value = "limit",defaultValue = "10") int limit){
        return ApiResult.resultPage(articleService.getList(page,limit),limit);
    }

}

