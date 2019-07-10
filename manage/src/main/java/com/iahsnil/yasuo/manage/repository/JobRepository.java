package com.iahsnil.yasuo.manage.repository;

import com.iahsnil.yasuo.manage.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zed
 * @Date: 2019/7/10 17:08
 * @Description:
 */
public interface JobRepository extends JpaRepository<JobInfo,Integer> {
}
