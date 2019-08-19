package com.iahsnil.nine.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iahsnil.nine.entity.VedioInfo;
import com.iahsnil.nine.repository.VedioRepository;
import com.iahsnil.nine.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
            log.info("script done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VedioInfo> getList() {
        return vedioRepository.findAll();
    }

    @Override
    public void download(int id) {

    }

    @Override
    @Async
    public Future<String> refreshList(int page) {
        StringBuilder sb = new StringBuilder();

        log.info("getList script process....");
        URL url = this.getClass().getClassLoader().getResource("pyscript/91_spider_list.py");
        if (null == url)
            throw new RuntimeException("script dont exist");
        try{
            String[] args1 = new String[] {"python", url.getPath().replaceFirst("/",""), String.valueOf(page)};
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
        JSONArray jsonArray = JSON.parseArray(sb.toString());
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            VedioInfo vedio = new VedioInfo();
            vedio.setCode(object.getString("key"));
            vedio.setLink(object.getString("link"));
            vedio.setName(object.getString("name"));
            vedio.setStatus((byte) 0);
            try {
                vedioRepository.saveAndFlush(vedio);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                log.error("重复的code........");
                log.error(vedio.getName());
            }
        }
        return new AsyncResult<>("success");
    }
}
