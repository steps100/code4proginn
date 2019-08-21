package com.metaship.edu.openapi.resource.enumeration;

/**
 * 资源类型
 * Created by None on 2018/7/28 12:11.
 * Wangyn All Rights Reserved.
 */
public enum ResourceTypeEnum {
    VIDEO(0, "视频"),
    AUDIO(1, "音频");

    private Integer code;
    private String message;

    ResourceTypeEnum(Integer code, String message) {
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
