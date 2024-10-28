package com.hos.hospital.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @Configurationc:声明配置类，可以代替spring的xml配置文件
 * 实现WebMvcConfigurer接口可以对springmvc进行扩展
 * WebMvcAutoConfiguration是springmvc的自动配置核心
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    /**
     * 在配置文件中获取文件的保存路径
     */
    @Value("${filePath}")
    private String filePath;




   /**
       * 添加拦截器规则
     * @param interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        //添加拦截器规则"
        InterceptorRegistration ir = interceptorRegistry.addInterceptor(new Interceptor());
        //addPathPatterns用于添加拦截
        //excludePathPatterns用于排除拦截，放行
    /*    ir.addPathPatterns("/**").
                excludePathPatterns("/api/**","/login/**","/behindstatic/**","/frontstatic/**","/files/**");*/
        ir.addPathPatterns("/admin/**","/doctor/**","/patient/**");
        
    }

    /**
     * 对资源的映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *   文件上传路径映射
         * addResourceHandler 指的是对外暴露的访问路径
         * addResourceLocations 指的是文件配置的目录
         */
        registry.addResourceHandler("/files/**")
                .addResourceLocations(filePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 解决跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                // 设置允许跨域请求的域名 
                .allowedOriginPatterns("*")
                // 是否允许跨域
                .allowCredentials(true)
                // 请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 跨域允许时间
                .maxAge(3600);

    }
}




