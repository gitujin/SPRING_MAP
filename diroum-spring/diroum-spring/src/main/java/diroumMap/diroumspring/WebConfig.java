/*
package diroumMap.diroumspring;

import diroumMap.diroumspring.interceptor.AdminInterceptor;
import diroumMap.diroumspring.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor()) //비회원X, 회원O
                .order(1)
                .addPathPatterns("/**") //막을 것
                .excludePathPatterns("/", "/signup", "/users/login", "/logout", "/board",
                        "/*.ico", "/css/**", "/error", "/js/**", "/users/join", "/Dairoum/**"); // 그냥 할 수 있는 것

        registry.addInterceptor(new AdminInterceptor()) // 회원, 비회원 X, 관리자 O
                .order(2)
                .addPathPatterns("/admin/**"); //막을 것

    }
}
*/
