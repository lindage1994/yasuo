package com.iahsnil.nine.service.impl;

import com.iahsnil.nine.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service("spiderService")
@Slf4j
public class SpiderServiceImpl implements SpiderService {
    @Override
    public void startSpider() {
        try{
            log.info("script process...");
            Process process = Runtime.getRuntime().exec("Python F:\\spriders\\91_spider.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                log.info(line);
            }
            in.close();
            process.waitFor();
            System.out.println("script done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
