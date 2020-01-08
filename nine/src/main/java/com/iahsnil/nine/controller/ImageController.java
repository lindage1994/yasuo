package com.iahsnil.nine.controller;

import com.iahsnil.nine.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;

/**
 * @Author: zed
 * @Date: 2019/10/31 14:51
 * @Description: 文字生成图片controller
 */
@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping("getImage")
    public ResponseEntity<byte[]> getImage(HttpServletResponse response, @RequestParam String words) throws IOException {
        // 通过python生成图片
        imageService.generateImage(words);
        // 返回图片流
        File file = new File("E://images/" + words + ".png");
        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add("Content-Disposition", "attachment; filename=Spring-Cloud.png");
        return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        return encodeToString(file);

//        FileInputStream fileInputStream = new FileInputStream(file);
//        OutputStream outputStream = response.getOutputStream();
//        byte[] bytes = new byte[1024];
//        while (-1 != fileInputStream.read(bytes)) {
//            outputStream.write(bytes);
//        }
    }

    /**
     * 将图片转换成base64格式进行存储
     * @param resource
     * @return
     */
    public static String encodeToString(File resource) throws IOException {
        String imageString = null;
        FileInputStream fileInputStream = new FileInputStream(resource);
        byte[] bytesString = null;
        try {
            bytesString = new byte[fileInputStream.available()];
            fileInputStream.read(bytesString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        imageString = Base64.getEncoder().encodeToString(bytesString);
        return imageString;
    }


}
