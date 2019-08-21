package com.metaship.edu.openapi.security.jwt;

import lombok.Data;

/**
 * Created by None on 2018/7/30 15:09.
 * Wangyn All Rights Reserved.
 */
@Data
public class JwtPayload {
    /**
     * 渠道，如elephant
     */
    private String channel;
    /**
     * 渠道用户的唯一id, 如公众号的openId, 跳转前端页面如http://m.dev.xuezheyoushi.com时必传
     */
    private String accountId;
}
