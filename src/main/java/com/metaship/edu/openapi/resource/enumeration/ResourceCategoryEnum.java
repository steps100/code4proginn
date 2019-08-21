package com.metaship.edu.openapi.resource.enumeration;

/**
 * Created by None on 2018/7/28 13:02.
 * Wangyn All Rights Reserved.
 */
public enum ResourceCategoryEnum {
    YI_GAO_KAO(0, "易高考"),
    XIN_KE_BIAO(1, "新课标");

    private Integer code;
    private String message;

    ResourceCategoryEnum(Integer code, String message) {
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
