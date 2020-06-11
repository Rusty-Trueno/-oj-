package com.spider.spider_oj.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rongshuai on 2020/6/11 11:16
 */
@Data
public class FinalScoreQueryDto implements Serializable {
    private static final long serialVersionUID = -2414916419723496953L;

    @ApiModelProperty(value = "csrftoken")
    String csrftoken;

    @ApiModelProperty(value = "sessionid")
    String sessionid;

    @ApiModelProperty(value = "测试id列表")
    List<Integer> contestIds;

    @ApiModelProperty(value = "分值差")
    Integer diff;
}
