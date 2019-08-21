package com.metaship.edu.openapi.settlement.dto;

import com.metaship.edu.openapi.settlement.enumeration.SettlementStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by None on 2018/7/29 00:33.
 * Wangyn All Rights Reserved.
 */
@Data
@ApiModel(value = "结算信息")
public class SettlementDTO {
    @ApiModelProperty(value = "结算时间", required = true)
    private Long createdTime;
    @ApiModelProperty(value = "总金额", required = true)
    private Double totalAmount;
    @ApiModelProperty(value = "结算比例", required = true)
    private Double ratio;
    @ApiModelProperty(value = "结算金额", required = true)
    private Double amount;
    @ApiModelProperty(value = "结算状态", required = true)
    private SettlementStatusEnum status;
}
