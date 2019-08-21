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
public class SettlementQueryDTO {
    @ApiModelProperty(value = "结算状态")
    private SettlementStatusEnum status;
}
