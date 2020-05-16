package com.spider.spider_oj.Service;

import com.spider.spider_oj.model.domain.ScoreDo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2020/3/20 21:21
 */
@Service
public class ScoreToExcelServiceImpl implements ScoreToExcelService{
    @Override
    public List<ScoreDo> writeScoreListToExcel(List<ScoreDo> scoreDoList,Integer contestId){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生oj成绩表");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "学生oj成绩表-" + contestId + ".xls";
        int rowNum = 1;
        String[] headers = {"排名","用户名","姓名","题数","罚时","A","B","C","D"};
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
            row1.createCell(8).setCellValue(scoreDo.getProblems().get(3).getPen());
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

}
