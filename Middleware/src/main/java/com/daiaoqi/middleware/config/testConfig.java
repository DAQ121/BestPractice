package com.daiaoqi.middleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: daiaoqi
 * @date: 2022/9/18
 **/

@Component
@ConfigurationProperties
public class testConfig {

    private List<Map<String, Object>> config;

    public void setTest(List<Map<String, Object>> config) {
        this.config = config;
    }

    @PostConstruct
    private void init() {
        System.out.println(this.config);
    }

}
