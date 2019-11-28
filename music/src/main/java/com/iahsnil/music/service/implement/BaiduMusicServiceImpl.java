package com.iahsnil.music.service.implement;

import com.iahsnil.music.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * @Author: zed
 * @Date: 2019/11/2 16:06
 * @Description:
 */
@Service
@Slf4j
public class BaiduMusicServiceImpl implements MusicService {

    @Async
    @Override
    public Future<String> downloadMusic(String name) {
        StringBuilder sb = new StringBuilder();

        log.info("download music script process....");
        URL url = this.getClass().getClassLoader().getResource("python/platforms/baiduFlac.py");
        if (null == url)
            throw new RuntimeException("script dont exist");
        try{
            String[] args1 = new String[] {"python", url.getPath().replaceFirst("/",""), name};
            Process process = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            process.waitFor();
            in.close();
            log.info(sb.toString());
        } catch (IOException e) {
            log.error("io exception......");
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error("线程interrupt......");
            e.printStackTrace();
        }
        log.info("api 异步执行完毕");
        return new AsyncResult<>("success");
    }
}
