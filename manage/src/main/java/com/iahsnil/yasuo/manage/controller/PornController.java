package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: zed
 * @Date: 2019/8/17 17:24
 * @Description: porn controller
 */
@Controller
@Slf4j
@RequestMapping("/porn")
public class PornController {

    @Autowired
    NineOneRemote nineOneRemote;

    @RequestMapping({"/","/list"})
    public String list(Model model, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum) {
        List<Object> list = nineOneRemote.getList();
        model.addAttribute("porns", list.stream().skip((pageNum - 1) * 10).limit(10).collect(Collectors.toList()));
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPages",list.size() / 10 + 1);
        return "porn/list";
    }

    @RequestMapping("/player")
    public String player(Model model,String url) {
        model.addAttribute("url",url);
        return "porn/player";
    }

    @RequestMapping("/refreshList")
    @ResponseBody
    public Object refreshList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return nineOneRemote.refreshList(page);
    }

    @RequestMapping("/getImage")
    @ResponseBody
    public void getImage(HttpServletResponse response, @RequestParam(value = "words") String words) throws IOException{
        ResponseEntity<byte[]> responseEntity = nineOneRemote.getImage(words);

//        File file = new File("tmp.png");
//        FileImageOutputStream imageOutputStream = new FileImageOutputStream(file);
//        imageOutputStream.write(responseEntity.getBody());

//        FileInputStream inputStream = new FileInputStream(file);

        response.setHeader("Content-type", MediaType.IMAGE_PNG_VALUE);
        response.setCharacterEncoding("utf-8");

        OutputStream output= response.getOutputStream();
//        byte[] bytes = new byte[1024];
//        while (-1 != inputStream.read(bytes)) {
//            output.write(bytes);
//        }
        output.write(responseEntity.getBody());
//        return nineOneRemote.getImage(words);
//        byte[] bytes = encodeToString(imageString);
//        OutputStream outputStream = response.getOutputStream();
//        outputStream.write(bytes);
    }

    /**
     * 将图片转换成base64格式进行存储
     * @param bytes
     * @return
     */
    public static String encodeToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
