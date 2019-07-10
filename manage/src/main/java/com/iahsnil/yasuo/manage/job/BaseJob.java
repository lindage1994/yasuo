package com.iahsnil.yasuo.manage.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: zed
 * @Date: 2019/7/10 17:10
 * @Description: 定时任务基类
 */
public interface BaseJob extends Job {
    void execute(JobExecutionContext context) throws JobExecutionException;
}
