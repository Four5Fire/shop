package cn.playcall.shop.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/**").addResourceLocations("file:/home/caojunyi/Desktop/文件管理/代码管理/Java/shop/src/main/resources/static/user/");
        registry.addResourceHandler("/shopUser/**").addResourceLocations("file:/home/caojunyi/Desktop/文件管理/代码管理/Java/shop/src/main/resources/static/shopUser/");
    }
}
