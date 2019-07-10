package com.iahsnil.yasuo.manage.Service.impl;

import com.iahsnil.yasuo.manage.Service.JobService;
import com.iahsnil.yasuo.manage.entity.JobInfo;
import com.iahsnil.yasuo.manage.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zed
 * @Date: 2019/7/10 17:30
 * @Description: 定时任务服务实现
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Page<JobInfo> getJobList(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public JobInfo findJobById(int id) {
        Optional<JobInfo> result = jobRepository.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException("未找到该任务....");
        }
        return result.get();
    }

    @Override
    public void save(JobInfo UserInfo) {

    }

    @Override
    public void edit(JobInfo UserInfo) {

    }

    @Override
    public void deleteById(int id) {

    }
}
