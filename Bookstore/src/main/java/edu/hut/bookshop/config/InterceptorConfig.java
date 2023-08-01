package edu.hut.bookshop.config;

import edu.hut.bookshop.interceptor.AdminInterceptor;
import edu.hut.bookshop.interceptor.ClientLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器注册类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {    //重写addInterceptors方法，在这个方法中注册拦截器
        //注册客户端拦截器
        registry.addInterceptor(new ClientLoginInterceptor())  //创建和注册一个拦截器ClientLoginInterceptor
                .addPathPatterns("/user_center/**")           //拦截路径以 "/user_center/", "/cart/", "/order/submit" 开头的请求
                .addPathPatterns("/cart/**")
                .addPathPatterns("/order/submit");
        //注册后台管理拦截器
        registry.addInterceptor(new AdminInterceptor())      //创建和注册一个拦截器AdminInterceptor()
                .addPathPatterns("/user/**").excludePathPatterns(new String[]{"/user/login","/user/register"}) //排除了一些特定路径
                .addPathPatterns("/order/**").excludePathPatterns("/order/submit")
                .addPathPatterns("/category/**")            //拦截路径以 "/user/", "/order/", "/category/", "/upload/book_image", "/book/" 开头的请求
                .addPathPatterns("/upload/book_image")
                .addPathPatterns("/book/**");
    }
}
