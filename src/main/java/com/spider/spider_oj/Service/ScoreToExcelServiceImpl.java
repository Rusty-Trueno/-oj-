package com.spider.spider_oj.Service;

import com.spider.spider_oj.model.domain.ContestDto;
import com.spider.spider_oj.model.domain.ResultDto;
import com.spider.spider_oj.model.domain.ScoreDo;
import com.spider.spider_oj.model.dto.ContestResultDto;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rongshuai on 2020/3/20 21:21
 */
@Service
public class ScoreToExcelServiceImpl implements ScoreToExcelService{
    @Override
    public List<ScoreDo> writeScoreListToExcel(List<ScoreDo> scoreDoList,Integer contestId,Integer diff){
        double per = 1.0*diff/scoreDoList.size();
        double total = 100;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生oj成绩表");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "学生oj成绩表-" + contestId + ".xls";
        int rowNum = 1;
        String[] headers = {"排名","用户名","姓名","题数","罚时","A","B","C","D","成绩"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for(ScoreDo scoreDo:scoreDoList){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(rowNum);
            row1.createCell(1).setCellValue(scoreDo.getUsername());
            row1.createCell(2).setCellValue(scoreDo.getNickname());
            row1.createCell(3).setCellValue(scoreDo.getAC());
            row1.createCell(4).setCellValue(scoreDo.getPen());
            row1.createCell(5).setCellValue(scoreDo.getProblems().get(0).getPen());
            row1.createCell(6).setCellValue(scoreDo.getProblems().get(1).getPen());
            row1.createCell(7).setCellValue(scoreDo.getProblems().get(2).getPen());
            row1.createCell(8).setCellValue(Double.valueOf(String.format("%.2f",total-(rowNum-1)*per)));
            rowNum++;
        }
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("D:/助教/C语言助教/oj成绩记录/"+fileName);
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return scoreDoList;
    }

    @Override
    public void generateFinalScore(List<ContestResultDto> resultList, Integer diff) {
        Map<String, ResultDto> map = new HashMap<>();
        for (ContestResultDto contestResultDto : resultList) {
            double per = 1.0*diff/contestResultDto.getScoreDoList().size();
            double total = 100;
            int cnt = 0;
            for (ScoreDo scoreDo : contestResultDto.getScoreDoList()) {
                ResultDto resultDto = map.getOrDefault(scoreDo.getUsername(),
                        new ResultDto(scoreDo.getUsername(),scoreDo.getNickname()));
                resultDto.getContestDtos().add(new ContestDto(contestResultDto.getContestId(),
                        Double.valueOf(String.format("%.2f",total-(cnt++)*per))));
                map.put(scoreDo.getUsername(),resultDto);
            }
        }

        //计算平均分
        double finalScore = 0;
        int cnt;
        for (ResultDto value: map.values()) {
            finalScore = 0;
            cnt = 0;
            for (ContestDto contestDto: value.getContestDtos()) {
                finalScore+=contestDto.getScore();
                cnt++;
            }
            value.setFinalScore(Double.valueOf(String.format("%.2f",finalScore/cnt)));
        }

        //生成excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生oj成绩表");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "学生oj成绩表（最终）" + ".xls";
        int rowNum = 1,next;
        StringBuilder headers = new StringBuilder();
        headers.append("排名,").append("用户名,").append("姓名,");
        for (ContestResultDto resultDto: resultList) {
            headers.append(resultDto.getContestId()).append(",");
        }
        headers.append("成绩");
        String[] headerList = headers.toString().split(",");
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headerList.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headerList[i]);
            cell.setCellValue(text);
        }
        for(ResultDto resultDto : map.values()){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(rowNum);
            row1.createCell(1).setCellValue(resultDto.getUsername());
            row1.createCell(2).setCellValue(resultDto.getNickname());
            next = 3;
            for (ContestDto contestDto:resultDto.getContestDtos()) {
                row1.createCell(next++).setCellValue(contestDto.getScore());
            }
            row1.createCell(headerList.length-1).setCellValue(resultDto.getFinalScore());
            rowNum++;
        }
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("D:/助教/C语言助教/oj成绩记录/"+fileName);
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
