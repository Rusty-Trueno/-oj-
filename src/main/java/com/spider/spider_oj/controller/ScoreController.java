package com.spider.spider_oj.controller;

import com.alibaba.fastjson.JSON;
import com.spider.spider_oj.Service.ScoreToExcelService;
import com.spider.spider_oj.model.domain.ScoreDo;
import com.spider.spider_oj.model.dto.ScoreQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rongshuai on 2020/3/20 19:01
 */
@RestController
@RequestMapping(value = "/score",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ScoreService",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ScoreController {

    @Autowired
    ScoreToExcelService scoreToExcelService;

    @PostMapping(value = "/getScoreInfoByContestId")
    @ApiOperation(httpMethod = "POST",value = "根据测验id获取测验成绩")
    public String getScoreInfoByContestId(@ApiParam(name = "ScoreQueryDto")@RequestBody ScoreQueryDto scoreQueryDto){
        Integer contestId = scoreQueryDto.getContestId();
        try {
            URI uri = new URIBuilder("http://10.105.242.83/api/contest/"+contestId+"/board/").build();
            BasicCookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie1 = new BasicClientCookie("csrftoken",scoreQueryDto.getCsrftoken());
            cookie1.setDomain("10.105.242.83");
            cookie1.setPath("/");
            BasicClientCookie cookie2 = new BasicClientCookie("sessionid",scoreQueryDto.getSessionid());
            cookie2.setDomain("10.105.242.83");
            cookie2.setPath("/");
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("accept","application/json, text/javascript, */*; q=0.01");
            httpGet.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            httpGet.addHeader("accept-encoding","gzip, deflate");
            httpGet.addHeader("connection","keep-alive");
            cookieStore.addCookie(cookie1);
            cookieStore.addCookie(cookie2);
            HttpClient client = HttpClientBuilder.create().
                    setDefaultCookieStore(cookieStore)
                    .build();
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            List<ScoreDo> scoreDoList = JSON.parseArray(responseEntity,ScoreDo.class);
            System.out.println(scoreToExcelService.writeScoreListToExcel(scoreDoList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
















}
