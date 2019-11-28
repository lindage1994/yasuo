package com.iahsnil.nine.service.impl;

import com.iahsnil.nine.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Author: zed
 * @Date: 2019/10/31 14:56
 * @Description:
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public void generateImage(String words) {
        log.info("generate image script process....");
        URL url = this.getClass().getClassLoader().getResource("pyscript/output_xiaozhuan.py");
        if (null == url)
            throw new RuntimeException("script dont exist");
        try{
            String[] args1 = new String[] {"python", url.getPath().replaceFirst("/",""),words};
            Process process = Runtime.getRuntime().exec(args1);
            int result = process.waitFor();
            FileInputStream errorStream = (FileInputStream)process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//读取
            System.out.println(isr.getEncoding());
            BufferedReader bufr = new BufferedReader(isr);//缓冲
            String line = null;
            while((line =bufr.readLine())!=null) {
                System.out.println(line);
            }
            isr.close();
            log.info("脚本执行结果：" + result);
        } catch (IOException e) {
            log.error("io exception......");
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error("线程interrupt......");
            e.printStackTrace();
        }
    }
}
