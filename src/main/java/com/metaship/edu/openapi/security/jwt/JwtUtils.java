package com.metaship.edu.openapi.security.jwt;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by None on 2018/7/30 16:37.
 * Wangyn All Rights Reserved.
 */
public class JwtUtils {

    public static final Map<String, String> CHANNEL_KEYS = ImmutableMap.<String, String>builder()
            .put("elephant", "PU8`k>4#?]aG>Gf")
            .put("1", "1")
            .build();


    public static String getSecretKey(String channel) {
        return CHANNEL_KEYS.get(channel);
    }
}
