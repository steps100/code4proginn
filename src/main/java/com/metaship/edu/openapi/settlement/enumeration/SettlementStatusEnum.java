package com.metaship.edu.openapi.settlement.enumeration;

/**
 * 结算状态
 * Created by None on 2018/7/29 10:56.
 * Wangyn All Rights Reserved.
 */
public enum SettlementStatusEnum {
    OPEN(0, "未结算"),
    SETTLED(1, "已结算");

    private Integer code;
    private String message;

    SettlementStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
