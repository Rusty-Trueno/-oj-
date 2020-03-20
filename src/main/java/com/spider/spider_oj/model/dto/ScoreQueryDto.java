package com.spider.spider_oj.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/3/20 19:16
 */
@Data
public class ScoreQueryDto implements Serializable {
    private static final long serialVersionUID = -2181760290990960182L;

//    @ApiModelProperty(value = "用户名")
//    String username;
//
//    @ApiModelProperty(value = "密码")
//    String password;

    @ApiModelProperty(value = "csrftoken")
    String csrftoken;

    @ApiModelProperty(value = "sessionid")
    String sessionid;

    @ApiModelProperty(value = "测试id")
    Integer contestId;
}
