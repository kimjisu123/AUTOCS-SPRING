package com.css.autocsfinal.config;

import com.css.autocsfinal.jwt.JwtAccessDeniedHandler;
import com.css.autocsfinal.jwt.JwtAuthenticationEntryPoint;
import com.css.autocsfinal.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig {

    // 토큰 디바이더 : JWT 토큰에 관련된 암호화, 복호화, 검증 로직은 다 이곳에서 이루어진다.
    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider
            , JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
            , JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    /* Security 설정을 무시할 정적 리소스 등록 */
    // 정적 리소스 (CSS, JS, 이미지 등)에 대한 보안 설정을 무시(해당 리소스들은 인증이나 인가가 필요하지 않기 때문)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
                "/lib/**");
    }

    /* HTTP요청에 대한 권한별 설정(기존 세션 인증 -> 토큰 인증으로 변경함 :> 추가적으로 코드가 더 생김) */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
            .csrf().disable()
            .exceptionHandling()
            /* 기본 시큐리티 설정에서 JWT 토큰과 관련된 유효성과 권한 체크용 설정*/
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // 필요한 권한 없이 접근(403)
            .accessDeniedHandler(jwtAccessDeniedHandler).and()      // 유효한 자격 증명 없을 시(401)
            .authorizeRequests()                                        // 요청에 대한 권한 설정
            .antMatchers("/").authenticated()                         // authenticated() : 사용자 인증 확인
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and() // permitAll() : 요청 허용 cors를 위해 preflight 요청 처리용 option요청 허용
            .cors().and()                                               // cors 프론트엔드, 백엔드를 구분지어서 개발하거나, 서로 다른 Server 환경에서 자원을 공유할 때, 설정이 안되어있으면 에러 발생
            .apply(new JwtSecurityConfig(tokenProvider));               // apply() : 시큐리티 설정 이후 추가 설정으로 jwt토큰 방식을 쓰겠다는 설정
        return http.build();
    }
    /* CORS(Cross-origin-resource-sharing) 설정용 Bean */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration configuration = new CorsConfiguration();                                  // CORS 설정하기 위한 객체
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();             // URL 패턴을 기반으로 CORS 설정을 관리

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));                     // setAllowedOrigins() : 요청이 허용된 도메인 목록 지정
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-type"  // setAllowedHeaders() : 허용되는 HTTP 헤더의 목록을 지정
                , "Access-Control-Allow-Headers", "Authorization"
                , "X-Requested-With"));
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin"));               // setExposedHeaders() : 서버에서 클라이언트로 응답할 때 노출할 수 있는 헤더의 목록을 지정
        configuration.addAllowedMethod("*");                                                         // addAllowedMethod() : Get/Post/Delete.. 등 요청의 모든 방식을 허용

        source.registerCorsConfiguration("/**", configuration);                               // 특정 URL이 아닌 모든 URL에 CORS 적용

        return source;
    }

}
