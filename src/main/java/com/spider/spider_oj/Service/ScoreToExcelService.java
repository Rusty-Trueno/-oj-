package com.spider.spider_oj.Service;

import com.spider.spider_oj.model.domain.ScoreDo;
import com.spider.spider_oj.model.dto.ContestResultDto;

import java.util.List;

/**
 * Created by rongshuai on 2020/3/20 21:21
 */
public interface ScoreToExcelService {

    List<ScoreDo> writeScoreListToExcel(List<ScoreDo> scoreDoList,Integer contestId,Integer diff);

    void generateFinalScore(List<ContestResultDto> resultList, Integer diff);
}
