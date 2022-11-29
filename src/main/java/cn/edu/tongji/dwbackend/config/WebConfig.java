package cn.edu.tongji.dwbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 配置全局跨域
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }

}
