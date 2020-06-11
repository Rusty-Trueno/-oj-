package com.spider.spider_oj.model.dto;

import com.spider.spider_oj.model.domain.ScoreDo;
import lombok.Data;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/11 11:36
 */
@Data
public class ContestResultDto {
    Integer contestId;

    List<ScoreDo> scoreDoList;

    public ContestResultDto(Integer contestId, List<ScoreDo> scoreDoList) {
        this.contestId = contestId;
        this.scoreDoList = scoreDoList;
    }
}
