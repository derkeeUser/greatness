package com.vivo.marked.hiveassistant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.config
 * @Author: chenxiaoming
 * @CreateTime: 2019-03-21 11:26
 * @Description: ...
 */
@Configuration
public class DefaultViewConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("../static/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
