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
@ApiModel(value = "资源")
public class ResourceDTO {
    @ApiModelProperty(value = "资源ID", required = true)
    private String id;
    @ApiModelProperty(value = "资源类型", required = true)
    private ResourceTypeEnum type;
    @ApiModelProperty(value = "学科", required = true)
    private ResourceSubjectEnum subject;
    @ApiModelProperty(value = "分类", required = true)
    private ResourceCategoryEnum category;
    @ApiModelProperty(value = "资源标题", required = true)
    private String title;
    @ApiModelProperty(value = "资源描述")
    private String description;
    @ApiModelProperty(value = "作者/讲师")
    private String author;
    @ApiModelProperty(value = "封面地址")
    private String coverUrl;
    @ApiModelProperty(value = "建议售价", required = true)
    private String suggestedPrice;
    @ApiModelProperty(value = "资源访问地址，访问时地址后面追加query param: token=${JWT token}", required = true)
    private String resourceUrl;
}
