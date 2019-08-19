package com.iahsnil.nine.service;

import com.iahsnil.nine.entity.VedioInfo;

import java.util.List;
import java.util.concurrent.Future;

public interface SpiderService {
    void startSpider();
    List<VedioInfo> getList();
    void download(int id);
    Future<String> refreshList(int pageNo);
}
