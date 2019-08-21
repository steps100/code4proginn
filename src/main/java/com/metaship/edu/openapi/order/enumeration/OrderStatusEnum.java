package com.metaship.edu.openapi.order.enumeration;

/**
 * 订单状态
 * Created by None on 2018/7/29 11:13.
 * Wangyn All Rights Reserved.
 */
public enum OrderStatusEnum {
    CREATED(0, "已创建"),
    SUCCESS(1, "交易完成"),
    FAILED(2, "交易失败"),
    CANCELED(3, "交易取消"),;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
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
