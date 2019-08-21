package com.metaship.edu.openapi.resource.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.metaship.edu.base.context.CommonContext;
import com.metaship.edu.consts.log.LogHelper;
import com.metaship.edu.exception.DataException;
import com.metaship.edu.openapi.exception.CustomException;
import com.metaship.edu.openapi.resource.dto.*;
import com.metaship.edu.openapi.resource.service.ResourceService;
import com.metaship.edu.res.course.bean.VideoDetailBean;
import com.metaship.edu.res.course.bean.RelatedCourseBean;
import com.metaship.edu.res.course.service.ICourseQueryService;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by None on 2018/7/28 16:49.
 * Wangyn All Rights Reserved.
 */
@RestController
@RequestMapping("/resources")
@Api(tags = "测试用接口", hidden = true)
public class ResourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;
    @Reference(version = "1.0", loadbalance = "random", cluster = "failover", retries = 2)
    private ICourseQueryService courseQueryService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    @ApiOperation(
            value = "${ResourceController.resources}",
            responseHeaders = {@ResponseHeader(name = "x-total-count", response = Integer.class)}
    )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "服务器内部错误"), //
            @ApiResponse(code = 403, message = "未授权访问"), //
            @ApiResponse(code = 500, message = "token无效或已过期")
    })
    public ResponseEntity<List<ResourceDTO>> getResources(
            @RequestHeader("x-channel") String channel,
            @ApiParam(name = "resourceQueryDTO") @RequestBody(required = false) ResourceQueryDTO resourceQueryDTO,
            @ApiParam(name = "page", required = true) @RequestParam Integer page,
            @ApiParam(name = "count", required = true) @RequestParam Integer count,
            HttpServletResponse response) {
        LinkedList<ResourceDTO> list = Lists.newLinkedList();
        Integer total = resourceService.getResources(list, channel, resourceQueryDTO, page, count);
        response.setHeader("x-total-count", String.valueOf(total == null ? 0 : total));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "${ResourceController.resources-by-id}",
            responseHeaders = {@ResponseHeader(name = "x-total-count", response = Integer.class)}
    )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "服务器内部错误"), //
            @ApiResponse(code = 403, message = "未授权访问"), //
            @ApiResponse(code = 500, message = "token无效或已过期")
    })
    public ResponseEntity<ResourceDTO> getResourceById(
            @RequestHeader("x-channel") String channel,
            @ApiParam(name = "id", required = true) @PathVariable String id
    ) {
        ResourceDTO resourceDTO = resourceService.getResourceById(id);
        return ResponseEntity.ok().body(resourceDTO);
    }
}
