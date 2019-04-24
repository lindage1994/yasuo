package com.iahsnil.nine.repository;

import com.iahsnil.nine.entity.VedioInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zed
 * @Date: 2019/4/24 20:10
 * @Description:
 */
public interface VedioRepository extends JpaRepository<VedioInfo,Integer> {
}
