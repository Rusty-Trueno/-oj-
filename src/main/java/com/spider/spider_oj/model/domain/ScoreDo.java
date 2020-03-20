package com.spider.spider_oj.model.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rongshuai on 2020/3/20 19:17
 */
@Data

public class ScoreDo implements Serializable {
    private static final long serialVersionUID = -5816989340785384613L;

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("AC")
    Integer AC;

    @ApiModelProperty("sub")
    Integer sub;

    @ApiModelProperty(value = "问题列表")
    List<ProblemDo> problems;

    @ApiModelProperty(value = "总罚时")
    Integer pen;

    @ApiModelProperty(value = "姓名")
    String nickname;
}
