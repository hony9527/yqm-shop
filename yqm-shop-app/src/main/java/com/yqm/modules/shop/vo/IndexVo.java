package com.yqm.modules.shop.vo;

import com.yqm.modules.activity.vo.YqmStoreCombinationQueryVo;
import com.yqm.modules.activity.vo.YqmStoreSeckillQueryVo;
import com.yqm.modules.mp.service.dto.YqmWechatLiveDto;
import com.yqm.modules.product.vo.YqmStoreProductQueryVo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("首页数据")
public class IndexVo {

    @ApiModelProperty("banner")
    private List<JSONObject> banner;
    //首页按钮
    @ApiModelProperty("首页按钮")
    private List<JSONObject> menus;
    //精品推荐->拼团
    @ApiModelProperty("精品推荐")
    private List<YqmStoreProductQueryVo> bastList;
    //首发新品->秒杀
    @ApiModelProperty("首发新品")
    private List<YqmStoreProductQueryVo> firstList;
    //猜你喜欢
    @ApiModelProperty("猜你喜欢")
    private List<YqmStoreProductQueryVo> benefit;
    //热门榜单
    @ApiModelProperty("热门榜单")
    private List<YqmStoreProductQueryVo> likeInfo;
    //滚动
    @ApiModelProperty("滚动")
    private List<JSONObject> roll;
    //地图key
    @ApiModelProperty("地图key")
    private String mapKey;
    //精品推荐->拼团
    @ApiModelProperty("精品推荐->拼团")
    private List<YqmStoreCombinationQueryVo> combinationList;
    //首发新品->秒杀
    @ApiModelProperty("首发新品->秒杀")
    private List<YqmStoreSeckillQueryVo> seckillList;
    //直播间信息
    @ApiModelProperty("直播间")
    private List<YqmWechatLiveDto> liveList;

}
