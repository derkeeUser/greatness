package com.vivo.marked.hiveassistant;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.util
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:02
 * @Description: ...
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HiveAssistantApplication.class);
    }

}
