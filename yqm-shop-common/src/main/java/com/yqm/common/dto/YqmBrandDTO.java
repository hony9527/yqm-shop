package com.yqm.common.dto;

import com.yqm.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author weiximei
 * @since 2022-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YqmBrandDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    private String updatedTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedBy;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态;delete 删除 success 有效
     */
    private String status;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌专区大图
     */
    private String maxLog;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 是否显示;show 显示 not_show 不显示
     */
    private String isShow;

    /**
     * 是否品牌制造商;0 不是 1是
     */
    private String isBrandManufacturers;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 子集合
     */
    private List<YqmBrandDTO> children;
}