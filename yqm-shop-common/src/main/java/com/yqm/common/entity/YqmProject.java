package com.yqm.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yqm.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 专题
 * </p>
 *
 * @author weiximei
 * @since 2022-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YqmProject extends BaseEntity implements Serializable {

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
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态;delete 删除 success 有效 failure 失效
     */
    private String status;

    /**
     * 分类id
     */
    private String classificationId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String imgs;

    /**
     * 主图
     */
    private String headerImg;

    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;


    /**
     * 正文内容
     */
    @TableField("`content`")
    private String content;

}