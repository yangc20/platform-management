package com.mysystem.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SystemService implements ApplicationContextAware {

    @Value("${system_env:dev}")
    private String env;

    private static SystemService SYSTEM_SERVICE;

    public static String getEnv() {
        return SYSTEM_SERVICE.env;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SYSTEM_SERVICE = applicationContext.getBean(SystemService.class);
        SYSTEM_SERVICE.env = this.env;
    }
}
