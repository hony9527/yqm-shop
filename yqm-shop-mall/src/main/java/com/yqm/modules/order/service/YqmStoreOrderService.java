/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yqmshop.cn
 * 注意：
 * 本软件为www.yqmshop.cn开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.yqm.modules.order.service;

import com.yqm.common.service.BaseService;
import com.yqm.modules.order.domain.YqmStoreOrder;
import com.yqm.modules.order.domain.YqmStoreOrderCartInfo;
import com.yqm.modules.order.param.OrderParam;
import com.yqm.modules.order.service.dto.OrderCountDto;
import com.yqm.modules.order.service.dto.OrderTimeDataDto;
import com.yqm.modules.order.service.dto.YqmStoreOrderDto;
import com.yqm.modules.order.service.dto.YqmStoreOrderQueryCriteria;
import com.yqm.modules.order.vo.ComputeVo;
import com.yqm.modules.order.vo.ConfirmOrderVo;
import com.yqm.modules.order.vo.OrderDataVo;
import com.yqm.modules.order.vo.ShoperOrderTimeDataVo;
import com.yqm.modules.order.vo.UserOrderCountVo;
import com.yqm.modules.order.vo.YqmStoreOrderQueryVo;
import com.yqm.modules.user.domain.YqmUser;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* @author weiximei
* @date 2020-05-12
*/
public interface YqmStoreOrderService  extends BaseService<YqmStoreOrder>{

    /**
     * 订单评价
     * @param orderCartInfo
     * @param user user
     * @param unique 订单orderCart唯一值
     * @param comment 评论内容
     * @param pics 图片
     * @param productScore 评分
     * @param serviceScore 评分
     */
    void orderComment(YqmStoreOrderCartInfo orderCartInfo, YqmUser user, String unique, String comment, String pics, String productScore,
                      String serviceScore);

    /**
     * 返回订单确认数据
     * @param yqmUser yqmUser
     * @param cartIds 购物车id
     * @return ConfirmOrderVO
     */
    ConfirmOrderVo confirmOrder(YqmUser yqmUser, String cartIds);



    /**
     * 确认订单退款
     * @param orderId 单号
     * @param price 金额
     * @param type ShopCommonEnum
     */
    void orderRefund(String orderId, BigDecimal price, Integer type);

    /**
     * 订单发货
     * @param orderId 单号
     * @param deliveryId 快递单号
     * @param deliveryName 快递公司code
     * @param deliveryType 快递方式
     */
    void orderDelivery(String orderId,String deliveryId,String deliveryName,String deliveryType);

    /**
     * 修改快递单号
     * @param orderId 单号
     * @param deliveryId 快递单号
     * @param deliveryName 快递公司code
     * @param deliveryType 快递方式
     */
    void updateDelivery(String orderId,String deliveryId,String deliveryName,String deliveryType);
    /**
     * 修改订单价格
     * @param orderId 单号
     * @param price 价格
     */
    void editOrderPrice(String orderId,String price);

    /**
     * 订单每月统计数据
     * @param page page
     * @param limit list
     * @return List<OrderDataVo>
     */
    List<OrderDataVo> getOrderDataPriceCount(int page, int limit);

    /**
     * 获取 今日 昨日 本月 订单金额
     * @return ShoperOrderTimeDataVo
     */
    ShoperOrderTimeDataVo getShoperOrderTimeData();

    /**
     * 获取拼团订单
     * @param pid 拼团id
     * @param uid 用户id
     * @return  YqmStoreOrder
     */
    YqmStoreOrder getOrderPink(Long pid,Long uid);


    /**
     * 未付款取消订单
     * @param orderId 订单号
     * @param uid 用户id
     */
    void cancelOrder(String orderId,Long uid);


    /**
     * 申请退款
     * @param explain 退款备注
     * @param Img 图片
     * @param text 理由
     * @param orderId 订单号
     * @param uid uid
     */
    void orderApplyRefund(String explain,String Img,String text,String orderId, Long uid);

    /**
     * 删除订单
     * @param orderId 单号
     * @param uid uid
     */
    void removeOrder(String orderId,Long uid);



    /**
     * 订单确认收货
     * @param orderId 单号
     * @param uid uid
     */
    void takeOrder(String orderId,Long uid);

    /**
     * 核销订单
     * @param verifyCode 核销码
     * @param isConfirm OrderInfoEnum
     * @param uid uid
     * @return YqmStoreOrderQueryVo
     */
    YqmStoreOrderQueryVo verifyOrder(String verifyCode, Integer isConfirm , Long uid);

    /**
     * 订单列表
     * @param uid 用户id
     * @param type OrderStatusEnum
     * @param page page
     * @param limit limit
     * @return list
     */
    Map<String,Object> orderList(Long uid,int type,int page,int limit);

    /**
     * 获取某个用户的订单统计数据
     * @param uid uid>0 取用户 否则取所有
     * @return UserOrderCountVo
     */
    UserOrderCountVo orderData(Long uid);

    /**
     * 处理订单返回的状态
     * @param order order
     * @return YqmStoreOrderQueryVo
     */
    YqmStoreOrderQueryVo handleOrder(YqmStoreOrderQueryVo order);

    /**
     * 支付成功后操作
     * @param orderId 订单号
     * @param payType 支付方式
     */
    void paySuccess(String orderId,String payType);

    /**
     * 余额支付
     * @param orderId 订单号
     * @param uid 用户id
     */
    void yuePay(String orderId,Long uid);

    /**
     * 积分兑换
     * @param orderId 订单号
     * @param uid 用户id
     */
    void integralPay(String orderId,Long uid);


    String aliPay(String orderId) throws Exception;


    /**
     * 创建订单
     * @param userInfo 用户信息
     * @param key key
     * @param param param
     * @return YqmStoreOrder
     */
    YqmStoreOrder createOrder(YqmUser userInfo, String key, OrderParam param);

    /**
     *计算订单价格
     * @param userInfo 用户
     * @param key 订单缓存key
     * @param couponId 优惠券id
     * @param useIntegral 使用积分 1-表示使用
     * @param shippingType 发货类型 OrderInfoEnum
     * @return ComputeVo
     */
    ComputeVo computedOrder(YqmUser userInfo, String key, String couponId,
                            String useIntegral, String shippingType,String addressId);

    /**
     * 订单信息
     * @param unique 唯一值或者单号
     * @param uid 用户id
     * @return YqmStoreOrderQueryVo
     */
    YqmStoreOrderQueryVo getOrderInfo(String unique, Long uid);





    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YqmStoreOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YqmStoreOrderDto>
    */
    List<YqmStoreOrder> queryAll(YqmStoreOrderQueryCriteria criteria);


    //YqmStoreOrderDto create(YqmStoreOrder resources);

    void update(YqmStoreOrder resources);
    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YqmStoreOrderDto> all, HttpServletResponse response) throws IOException;

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    YqmStoreOrderDto getOrderDetail(Long orderId);

    Map<String,Object> queryAll(List<String> ids);




    /**
     * 根据商品分类统计订单占比
     * @return OrderCountDto
     */
    OrderCountDto getOrderCount();

    /**
     * 首页订单/用户等统计
     * @return OrderTimeDataDto
     */
    OrderTimeDataDto getOrderTimeData();

    /**
     * 返回本月订单金额与数量
     * @return map
     */
    Map<String,Object> chartCount();


   void retrunStock(String orderId);
}
