package com.yqm.modules.product.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AttrValueDTO
 * @Author weiximei <610796224@qq.com>
 * @Date 2019/10/23
 **/
@Data
public class AttrValueDto {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "属性")
    private String attr;

    @ApiModelProperty(value = "是否选择")
    private Boolean check = false;
}
