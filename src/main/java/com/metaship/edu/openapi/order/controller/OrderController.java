package com.metaship.edu.openapi.order.controller;

import com.metaship.edu.openapi.order.dto.OrderDTO;
import com.metaship.edu.openapi.order.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by None on 2018/7/28 20:59.
 * Wangyn All Rights Reserved.
 */
@RestController
@RequestMapping("/orders")
@Api(tags = "orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    @ApiOperation(
            value = "${OrderController.addOrder}"
    )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "服务器内部错误"),
            @ApiResponse(code = 403, message = "未授权访问"),
            @ApiResponse(code = 500, message = "token无效或已过期")
    })
    public ResponseEntity<String> addOrder(
            @RequestHeader("x-channel") String channel,
            @ApiParam(name = "orderDTO", required = true) @RequestBody OrderDTO orderDTO
    ) {
        orderService.addOrder(orderDTO);
        return ResponseEntity.created(URI.create("/orders")).body("success");
    }
}
