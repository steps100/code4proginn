package com.metaship.edu.openapi.order.dto;

import com.metaship.edu.openapi.order.enumeration.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by None on 2018/7/28 20:33.
 * Wangyn All Rights Reserved.
 */
@Data
@ApiModel(value = "订单信息")
public class OrderDTO {
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNo;
    @ApiModelProperty(value = "用户id", required = true)
    private String accountId;
    @ApiModelProperty(value = "订购资源列表", required = true)
    private List<String> resourceIdList;
    @ApiModelProperty(value = "订购时间", required = true)
    private Long purchaseTime;
    @ApiModelProperty(value = "过期时间", required = true)
    private Long invalidTime;
    @ApiModelProperty(value = "订单金额", required = true)
    private Long price;
    @ApiModelProperty(value = "订单状态", required = true)
    private OrderStatusEnum status;
    @ApiModelProperty(value = "创建时间", required = true)
    private Long createdTime;
    @ApiModelProperty(value = "是否购买全部资源 1-是 2-否", required = true)
    private Integer purchaseAllResource;
}
