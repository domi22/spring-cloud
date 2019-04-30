package spring.boot.common.function.multi.datasource.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.boot.common.function.multi.datasource.aop.DynamicDataSourceInterceptor;

/**
 * WebMvcConfigurerAdapter在boot2.0过时了，官方推荐用WebMvcConfigurer
 * https://www.jianshu.com/p/d47a09532de7
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DynamicDataSourceInterceptor())
                .addPathPatterns("/**")
                .order(1);
    }
}
