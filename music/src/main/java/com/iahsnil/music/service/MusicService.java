package com.iahsnil.music.service;

import java.util.concurrent.Future;

/**
 * @Author: zed
 * @Date: 2019/11/2 16:04
 * @Description:
 */
public interface MusicService {

    Future<String> downloadMusic(String name);

}
