package com.metaship.edu.openapi.account.enumeration;

/**
 * 性别
 * Created by None on 2018/7/28 19:55.
 * Wangyn All Rights Reserved.
 */
public enum GenderEnum {
    MALE(0, "男"),
    FEMALE(1, "女");

    private Integer code;
    private String message;

    GenderEnum(Integer code, String message) {
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
