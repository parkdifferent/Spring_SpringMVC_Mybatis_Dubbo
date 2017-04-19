package com.szkingdom.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianf on 2016/8/9.
 */


@Controller
@RequestMapping(value="/mvc",method= RequestMethod.POST)
public class UploadController {

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception {
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(req.getSession().getServletContext().getRealPath("/")+ "upload");
        File file1 = new File(req.getSession().getServletContext().getRealPath("/")+ "upload");
        if(!file1.exists()) {
            file1.mkdir();
        }
        FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+ "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return "success";
    }
}
