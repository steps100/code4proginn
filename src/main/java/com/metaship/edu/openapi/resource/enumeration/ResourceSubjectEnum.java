package com.metaship.edu.openapi.resource.enumeration;

/**
 * 学科
 * Created by None on 2018/7/28 13:03.
 * Wangyn All Rights Reserved.
 */
public enum ResourceSubjectEnum {
    CHINESE(0, "语文"),
    MATHMATICS(1, "数学"),
    ENGLISH(2, "英语"),
    PHYSICS(3, "物理"),
    CHEMISTRY(4, "化学"),
    POLITICS(5, "政治"),
    HISTORY(6, "历史");


    private Integer code;
    private String message;

    ResourceSubjectEnum(Integer code, String message) {
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
