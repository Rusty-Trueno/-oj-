package com.spider.spider_oj.model.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rongshuai on 2020/6/11 11:05
 */
@Data
public class ResultDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String nickname;

    @ApiModelProperty(value = "测验成绩列表")
    List<ContestDto> contestDtos;

    @ApiModelProperty(value = "平均成绩")
    double finalScore;

    public ResultDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
        this.contestDtos = new ArrayList<>();
    }
}
