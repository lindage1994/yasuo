package com.iahsnil.yasuo.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Auther: zed
 * @Date: 2019/4/23 19:16
 * @Description: redis spring session
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
