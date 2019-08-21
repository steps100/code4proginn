package com.metaship.edu.openapi.resource.dto;

import com.metaship.edu.base.serialize.Readable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author QuentinBo
 * @mail boboeree@qq.com
 * @create 2017/7/18.
 * @time 14:36
 * 简略的课程对象
 */
@Data
public final class ResourceSimpleDTO extends Readable {
    /**
     * id
     */
    @ApiModelProperty(name = "id",value = "课程id")
    private String id;
    /**
     * 课程名称
     */
    @ApiModelProperty(name = "name",value = "课程名称")
    private String name;
    /**
     * 作者
     */
    @ApiModelProperty(name = "authors",value = "作者")
    private List<String> authors;
    /**
     * 首图地址
     */
    @ApiModelProperty(name = "coverUrl",value = "首图地址")
    private String coverUrl;
    /**
     * 视频时长
     */
    @ApiModelProperty(name = "duration",value = "视频时长 单位：秒")
    private Double duration;
    /**
     * 视频播放次数
     */
    @ApiModelProperty(name = "videoPlayCount",value = "视频播放次数")
    private Integer videoPlayCount;
    /**
     * 是否可用 1-是 2-否
     */
    @ApiModelProperty(name = "isUsable", value = "是否可用 1-是 2-否")
    private Integer isUsable;
}
