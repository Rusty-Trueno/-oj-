package com.spider.spider_oj.model.domain;

import lombok.Data;

/**
 * Created by rongshuai on 2020/6/11 11:07
 */
@Data
public class ContestDto {

    Integer contestId;

    Double score;

    public ContestDto(Integer contestId, Double score) {
        this.contestId = contestId;
        this.score = score;
    }
}
