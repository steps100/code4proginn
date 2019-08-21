package com.metaship.edu.openapi.resource.dto;

import com.metaship.edu.base.serialize.Readable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public final class RelatedResourceDTO extends Readable {
    /**
     * 课程id
     */
    @ApiModelProperty(name = "courseId", value = "课程id")
    private String courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(name = "courseName", value = "课程名称")
    private String courseName;
    /**
     * 学期
     */
    @ApiModelProperty(name = "terms", value = "学期")
    private List<Integer> terms;
    /**
     * 是否可用 1-是 2-否
     */
    @ApiModelProperty(name = "isUsable", value = "是否可用 1-是 2-否")
    private Integer isUsable;
}
