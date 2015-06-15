package net.zhigang.dante.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "net.zhigang.dante", 
        excludeFilters = {
            @Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class),
            @Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfig.class),
            @Filter(type = FilterType.ASSIGNABLE_TYPE, value = DatabaseConfig.class)
        })
public class AppConfig {
    
}
