package com.szkingdom.ssm.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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


    @RequestMapping("/export")
    public void exportExcel() {
        List<Record> recordList = recordService.selectRecordList();
        recordService.exportExcel(recordList);
        try {
            InputStream stream = new FileInputStream(new File("D:/records.xlsx"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    @RequestMapping("/recordList")
    public ModelAndView recordList() {
        List<Record> recordList = recordService.selectRecordList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("recordList",recordList);
        modelAndView.setViewName("list");
        return modelAndView;
    }



/*  @RequestMapping(value = "/showQuestions",method = RequestMethod.GET)
    public Object showQuestions(Model model, @RequestParam Optional<String> tag,
                                @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        PageHelper.startPage(page.orElse(PAGE), pageSize.orElse(PAGE_SIZE), "createdTime desc");
        if(tag.isPresent()) {
            model.addAttribute("questions", new PageInfo<>(questionMapper.findByTag(tag.get())));
        } else {
            model.addAttribute("questions", new PageInfo<>(questionMapper.findAll()));
        }
        return "list";
    }*/


}
