package com.szkingdom.ssm.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tianf on 2016/10/31.
 */


@Controller
@RequestMapping("record")
public class RecordController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRecordService recordService;


    @RequestMapping("/recordList")
    public ModelAndView recordList() {
        List<Record> recordList = recordService.selectRecordList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("recordList",recordList);
        modelAndView.setViewName("list");
        return modelAndView;
    }


}
