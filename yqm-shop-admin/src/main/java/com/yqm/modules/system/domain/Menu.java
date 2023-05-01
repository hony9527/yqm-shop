/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yqmshop.cn
* 注意：
* 本软件为www.yqmshop.cn开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.yqm.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yqm.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author weiximei
* @date 2020-05-14
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("menu")
public class Menu extends BaseDomain {

    /** ID */
    @TableId
    private Long id;


    /** 是否外链 */
    private Boolean iFrame;


    /** 菜单名称 */
    @NotBlank(message = "请填写菜单名称")
    private String name;


    /** 组件 */
    private String component;


    /** 上级菜单ID */
    @NotNull(message = "上级菜单ID不能为空")
    private Long pid;


    /** 排序 */
    @NotNull(message = "排序不能为空")
    private Long sort;


    /** 图标 */
    private String icon;


    /** 链接地址 */
    private String path;


    /** 缓存 */
    private Boolean cache;


    /** 是否隐藏 */
    private Boolean hidden;


    /** 组件名称 */
    private String componentName;




    /** 权限 */
    private String permission;


    /** 类型，目录、菜单、按钮 */
    private Integer type;


    public void copy(Menu source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
