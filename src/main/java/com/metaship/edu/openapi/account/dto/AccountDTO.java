package com.metaship.edu.openapi.account.dto;

import com.metaship.edu.openapi.account.enumeration.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by None on 2018/7/27 22:49.
 * Wangyn All Rights Reserved.
 */
@Data
@ApiModel(value = "账号/用户信息")
public class AccountDTO {
    @ApiModelProperty(value = "用户id", required = true)
    @NotNull
    private String accountId;
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;
    @ApiModelProperty(value = "头像地址", required = true)
    private String avatarUrl;
    @ApiModelProperty(value = "性别", required = true)
    /**
     * @see GenderEnum
     */
    private Integer gender;
    @ApiModelProperty(value = "区域", required = true)
    private String region;
    @ApiModelProperty(value = "手机号码")
    private String cellPhoneNumber;
    @ApiModelProperty(value = "所在年级1-12")
    private Integer grade;
    @ApiModelProperty(value = "用户信息变更时间", required = true)
    private Long modifiedTime;
    @ApiModelProperty(value = "用户创建时间", required = true)
    private Long createdTime;
}
