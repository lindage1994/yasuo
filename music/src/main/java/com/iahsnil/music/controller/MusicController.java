package com.iahsnil.music.controller;

import com.iahsnil.music.service.MusicService;
import lombok.extern.slf4j.Slf4j;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: zed
 * @Date: 2019/11/2 15:14
 * @Description:
 */
@RestController
@Slf4j
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping("search")
    public String search(String name) {
        return null;
    }

    @RequestMapping("download")
    public String download(String name) {
        Future<String> future = musicService.downloadMusic(name);
        Thread listerTread = new Thread(() -> {
            try {
                log.info("调用音乐api异步下载返回结果:" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        listerTread.setDaemon(true);
        listerTread.start();
        return "success";
    }

    @RequestMapping("list")
    public ResponseEntity<byte[]> list(HttpServletResponse response, @RequestParam String words) throws IOException {
        // 返回图片流
        File file = new File("E://music/");

        String path = null;

        if (file.isDirectory()) {
            path = file.getAbsolutePath() + "\\" + file.list()[0];
        }

        File music = new File(path);

        FileSystemResource resource = new FileSystemResource(music);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add("Content-Disposition", "attachment; filename=music.flac");
        return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(FileCopyUtils.copyToByteArray(resource.getInputStream()));

    }


}
