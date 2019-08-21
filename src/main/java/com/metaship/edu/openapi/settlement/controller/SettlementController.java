package com.metaship.edu.openapi.settlement.controller;

import com.google.common.collect.Lists;
import com.metaship.edu.openapi.settlement.dto.SettlementDTO;
import com.metaship.edu.openapi.settlement.dto.SettlementQueryDTO;
import com.metaship.edu.openapi.settlement.service.SettlementService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by None on 2018/7/29 11:50.
 * Wangyn All Rights Reserved.
 */
@RestController
@RequestMapping("/settlements")
@Api(tags = "settlements")
public class SettlementController {

    @Autowired
    private SettlementService settlementService;

    @GetMapping("/")
    @ApiOperation(
            value = "${SettlementController.settlements}",
            responseHeaders = {@ResponseHeader(name = "x-total-count", response = Integer.class)}
    )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "服务器内部错误"), //
            @ApiResponse(code = 403, message = "未授权访问"), //
            @ApiResponse(code = 500, message = "token无效或已过期")
    })
    public ResponseEntity<List<SettlementDTO>> settlements(
            @RequestHeader("x-channel") String channel,
            @ApiParam(name = "settlementQueryDTO") @RequestBody(required = false) SettlementQueryDTO settlementQueryDTO,
            @ApiParam(name = "page", required = true) @RequestParam Integer page,
            @ApiParam(name = "count", required = true) @RequestParam Integer count,
            HttpServletResponse response
    ) {
        LinkedList<SettlementDTO> list = Lists.newLinkedList();
        Integer total = settlementService.getSettlements(list, channel, settlementQueryDTO, page, count);
        response.setHeader("x-total-count", String.valueOf(total == null ? 0 : total));
        return ResponseEntity.ok().body(list);
    }
}
