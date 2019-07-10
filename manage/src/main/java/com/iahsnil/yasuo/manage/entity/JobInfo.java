package com.iahsnil.yasuo.manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: zed
 * @Date: 2019/7/10 17:03
 * @Description: 定时任务实体类
 */
@Entity
@Getter
@Setter
public class JobInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String group_name;
    private String class_name;
    private String trigger_name;
    private String trigger_group;
    private BigInteger repeat_interval;
    private BigInteger times_triggered;
    private String cron_expression;
    private String time_zone;
}
