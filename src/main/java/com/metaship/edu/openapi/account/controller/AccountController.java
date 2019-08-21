package com.metaship.edu.openapi.account.controller;

import com.metaship.edu.base.context.CommonContext;
import com.metaship.edu.base.param.Base;
import com.metaship.edu.openapi.account.dto.AccountDTO;
import com.metaship.edu.openapi.account.service.AccountService;
import com.metaship.edu.openapi.resource.dto.ResourceDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by None on 2018/7/28 11:31.
 * Wangyn All Rights Reserved.
 */
@RestController
@RequestMapping("/accounts")
@Api(tags = "accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @ApiOperation(
            value = "${AccountController.login}"
    )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "服务器内部错误"),
            @ApiResponse(code = 403, message = "未授权访问"),
            @ApiResponse(code = 500, message = "token无效或已过期")
    })
    public ResponseEntity<String> login(
            @RequestHeader("x-channel") String channel,
            @ApiParam(name = "accountDTO", required = true) @RequestBody AccountDTO accountDTO
    ) {
        accountService.create(channel, accountDTO);
        Base base = new Base();
        base.setUid(accountDTO.getAccountId());
        CommonContext.set(base);
        return ResponseEntity.ok("success");
    }
}
