package com.szkingdom.ssm.service.impl;

import com.szkingdom.ssm.dao.RecordMapper;
import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianf on 2016/10/31.
 */

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Transactional(rollbackFor = Exception.class)
   /* 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)*/
    public int deleteByPrimaryKey(Integer id) {
        return recordMapper.deleteByPrimaryKey(id);
    }

    public int insert(Record record) {
        return recordMapper.insert(record);
    }

    public int insertSelective(Record record) {
        return recordMapper.insertSelective(record);
    }

    public Record selectByPrimaryKey(Integer id) {
        return recordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Record record) {
        return recordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Record record) {
        return recordMapper.updateByPrimaryKey(record);
    }

    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
    /*在整个方法运行前就不会开启事务,这样就做成一个只读事务，可以提高效率。*/
    public List<Record> selectRecordList() {
        return recordMapper.selectRecordList();
    }

    public void exportExcel(List<Record> list) {

        //创建excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建第一个sheet（页），命名为 new sheet
        XSSFSheet sheet = workbook.createSheet("new sheet");


        // 创建单元格，并设置值表头 设置表头居中
        // General font
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);

        // create cell style group
        // XSSFCellStyle[] cellStyles = new XSSFCellStyle[20];

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 创建标题行
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("url");
        header.createCell(2).setCellValue("question");
        header.createCell(3).setCellValue("upvote");

        int rowNum = 1;
        for (Record record : list) {
            XSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getUrl());
            row.createCell(2).setCellValue(record.getQuestion());
            row.createCell(3).setCellValue(record.getUpvote());
            rowNum++;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("D:/records.xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
