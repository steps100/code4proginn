package com.metaship.edu.openapi.resource.dto;

import com.metaship.edu.openapi.resource.enumeration.ResourceCategoryEnum;
import com.metaship.edu.openapi.resource.enumeration.ResourceSubjectEnum;
import com.metaship.edu.openapi.resource.enumeration.ResourceTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by None on 2018/7/28 12:09.
 * Wangyn All Rights Reserved.
 */
@Data
@ApiModel(value = "资源查询")
public class ResourceQueryDTO {
    @ApiModelProperty(value = "资源类型")
    private ResourceTypeEnum type;
    @ApiModelProperty(value = "学科")
    private ResourceSubjectEnum subject;
    @ApiModelProperty(value = "分类")
    private ResourceCategoryEnum category;
    @ApiModelProperty(value = "资源标题")
    private String title;
    @ApiModelProperty(value = "作者/讲师")
    private String author;
}
