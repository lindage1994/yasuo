package com.iahsnil.nine.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author: zed
 * @Date: 2019/10/30 17:05
 * @Description: mongodb entity
 */
@Document(collection = "vedio_info")
public class Vedio {
    @Field("code")
    private String code;
    @Field("link")
    private String link;
    @Field("name")
    private String name;
    @Field("status")
    private Integer status;
}
