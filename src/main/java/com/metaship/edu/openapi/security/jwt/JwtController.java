package com.metaship.edu.openapi.security.jwt;

import com.metaship.edu.openapi.exception.CustomException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by None on 2018/7/30 14:50.
 * Wangyn All Rights Reserved.
 */
@RestController
@RequestMapping("/jwt")
@Api(tags = "测试用接口", hidden = true)
public class JwtController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth")
    public ResponseEntity<String> getJwt(
            @RequestBody JwtPayload payload
    ) {
        String secretKey = JwtUtils.getSecretKey(payload.getChannel());
        if (StringUtils.isEmpty(secretKey)) {
            throw new CustomException("渠道信息有误", HttpStatus.BAD_REQUEST);
        }
        String jwt = jwtTokenProvider.createToken(payload, secretKey);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/check")
    public ResponseEntity<String> checkJwt(
            @RequestHeader(name = "x-channel") String channel
    ) {

        return ResponseEntity.ok("success");
    }
}
