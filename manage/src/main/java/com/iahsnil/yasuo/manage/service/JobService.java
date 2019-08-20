package com.iahsnil.yasuo.manage.service;

import com.iahsnil.yasuo.manage.entity.JobInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: zed
 * @Date: 2019/7/10 17:29
 * @Description: 定时任务接口
 */
public interface JobService {
    Page<JobInfo> getJobList(Pageable pageable);

    JobInfo findJobById(int id);

    void save(JobInfo UserInfo);

    void edit(JobInfo UserInfo);

    void deleteById(int id);
}
