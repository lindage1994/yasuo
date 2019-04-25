package com.iahsnil.nine.service;

import java.util.concurrent.Future;

public interface SpiderService {
    void startSpider();
    Object getList();
    void download(int id);
    Future<String> refreshList(int pageNo);
}
