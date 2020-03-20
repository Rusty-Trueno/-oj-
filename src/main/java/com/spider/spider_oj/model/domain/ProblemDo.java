package com.spider.spider_oj.model.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/3/20 20:21
 */
@Data
public class ProblemDo implements Serializable {
    private static final long serialVersionUID = -6488281556246294485L;

    @ApiModelProperty(value = "AC")
    Integer AC;

    @ApiModelProperty(value = "sub")
    Integer sub;

    @ApiModelProperty(value = "idx")
    String idx;

    @ApiModelProperty(value = "pen")
    Integer pen;

    @ApiModelProperty(value = "fb")
    Boolean fb;

    @ApiModelProperty(value = "ac_time")
    Integer ac_time;
}
