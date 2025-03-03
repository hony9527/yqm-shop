/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn
 * 注意：
 * 本软件为www.yqmshop.cn开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.yqm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author weiximei
 * 短信类型枚举
 */
@Getter
@AllArgsConstructor
public enum SmsTypeEnum {
    BIND("bind","绑定手机短信"),
    LOGIN("login","登陆短信"),
    REGISTER("register","注册短信");

    private String value;
    private String desc;

    public static SmsTypeEnum toType(String value) {
        return Stream.of(SmsTypeEnum.values())
                .filter(c -> c.value == value)
                .findAny()
                .orElse(null);
    }
}
