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

package com.yqm.common.event.refundPay;

import com.yqm.common.dto.YqmRefundPayDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 确认退款事件
 *
 * @Author: weiximei
 * @Date: 2022/5/5 20:12
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
public class ConfirmRefundPayEvent extends ApplicationEvent {

    private YqmRefundPayDTO refundPayDTO;

    public ConfirmRefundPayEvent(YqmRefundPayDTO source) {
        super(source);
        this.refundPayDTO = source;
    }

    public YqmRefundPayDTO getStoreSkuDTO() {
        return refundPayDTO;
    }
}
