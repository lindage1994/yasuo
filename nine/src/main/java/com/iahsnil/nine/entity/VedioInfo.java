package com.iahsnil.nine.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: zed
 * @Date: 2019/4/24 20:05
 * @Description: vedio info
 */
@Entity
@Data
public class VedioInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String code;
    private String name;
    private String link;
    private Byte status;
}
