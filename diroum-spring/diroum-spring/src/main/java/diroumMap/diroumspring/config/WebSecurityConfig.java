package diroumMap.diroumspring.config;

import diroumMap.diroumspring.security.FormAuthenticationProvider;
import diroumMap.diroumspring.security.MyLoginFailureHandler;
import diroumMap.diroumspring.security.MyLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                // 요청에 대한 사용권한 체크
                .authorizeRequests()
                .antMatchers("/", "/signup", "/users/login", "/logout",
                        "/*.ico", "/resource/**", "/css/**", "/error", "/js/**", "/users/join", "/Dairoum/**").permitAll()
                .antMatchers("/users/**", "/board/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                //cors방지
                .cors().disable()
                // CSRF 프로텍션을 비활성화
                .csrf().disable()
                .authorizeRequests();

                // Form Login 사용
        http.authorizeRequests(request -> request.anyRequest().authenticated());
        http.formLogin() // 로그인 기능 적용
                .loginPage("/users/login") // 커스텀 로그인 페이지 설정
                .usernameParameter("loginId") // form 아이디 파라미터 키 값
                .passwordParameter("password") // form 비밀번호 파라미터 키 값
                .successHandler(new MyLoginSuccessHandler())
                .failureHandler(new MyLoginFailureHandler()); // 로그인 실패 핸들러
        http.logout()
                .logoutSuccessUrl("/login") //로그아웃 성공 시 리다이렉트 주소
                .invalidateHttpSession(true); // 세션 날리기


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 할때 사용할 BCrypthPasswordEncoder 를 빈으로 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new FormAuthenticationProvider();
    }

}
