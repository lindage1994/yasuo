package com.iahsnil.yasuo.manage.Service;


import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: zed
 * @Date: 2019/4/23 19:34
 * @Description: 91 task service
 */
public interface NineOneTaskService extends TaskService {
    /**
     * 刷新视频链接
     * @return success or fail
     */
    JSONObject refreshList();

    /**
     * 获取视频链接
     * @return vedio list
     */
    JSONObject getList();

    /**
     * 执行下载视频
     * @param taskId 视频任务id
     * @return success or fail
     */
    JSONObject excuteTask(int taskId);
}
