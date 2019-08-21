package com.metaship.edu.openapi.resource.dto;

import com.metaship.edu.base.serialize.Readable;
import com.metaship.edu.res.course.bean.CourseAuthorInfoBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public final class ResourceDetailDTO extends Readable {
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
     * 首图地址
     */
    @ApiModelProperty(name = "cover", value = "首图地址")
    private String cover;
    /**
     * 作者信息
     */
    @ApiModelProperty(name = "courseAuthorInfo", value = "作者信息")
    private List<CourseAuthorInfoBean> courseAuthorInfo;
    /**
     * 课程介绍
     */
    @ApiModelProperty(name = "courseIntroduce", value = "课程介绍")
    private String courseIntroduce;
    /**
     * 学期
     */
    @ApiModelProperty(name = "terms", value = "学期")
    private List<Integer> terms;
    /**
     * 视频播放凭证
     */
    @ApiModelProperty(name = "playAuth", value = "视频播放凭证")
    private String playAuth;
    /**
     * 资源id
     */
    @ApiModelProperty(name = "resourceId", value = "资源id")
    private String resourceId;
}
