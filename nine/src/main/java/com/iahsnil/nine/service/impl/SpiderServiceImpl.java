package com.iahsnil.nine.service.impl;

import com.iahsnil.nine.service.SpiderService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service("spiderService")
public class SpiderServiceImpl implements SpiderService {
    @Override
    public void startSpider() {
        try{
            Process process = Runtime.getRuntime().exec("Python G:\\spriders\\test.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
