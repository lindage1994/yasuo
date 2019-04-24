package com.iahsnil.nine.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iahsnil.nine.entity.VedioInfo;
import com.iahsnil.nine.repository.VedioRepository;
import com.iahsnil.nine.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

@Service("spiderService")
@Slf4j
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    VedioRepository vedioRepository;


    @Override
    public void startSpider() {
        try{
            log.info("script process...");
            Process process = Runtime.getRuntime().exec("Python /pyscript/91_spider_list.py ");
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

    @Override
    public void getList(int pageNo) {
        StringBuilder sb = new StringBuilder();
        try{
            log.info("getList script process....");
            String[] args1 = new String[] {"python", "F:\\spriders\\91_spider_list.py", String.valueOf(pageNo)};
            Process process = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            process.waitFor();
            in.close();
        } catch (IOException e) {
            log.error("io exception......");
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error("线程interrupt......");
            e.printStackTrace();
        }
        Set<VedioInfo> vedioInfoSet = new HashSet<>();
        JSONArray jsonArray = JSON.parseArray(sb.toString());
        for (Object o : jsonArray) {
            JSONObject object = JSON.parseObject(o.toString());
            VedioInfo vedio = new VedioInfo();
            vedio.setCode(object.getString("key"));
            vedio.setLink(object.getString("link"));
            vedio.setName(object.getString("name"));
            vedio.setStatus((byte) 0);
            vedioInfoSet.add(vedio);
        }
        vedioRepository.saveAll(vedioInfoSet);
    }

    @Override
    public void download(int id) {

    }
}