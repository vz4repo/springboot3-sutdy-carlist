package org.example.mycardata.web;

import java.util.Collections;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


// 없어진 WebSecurityConfigurerAdapter
// 해결: SecurityFilterChain @Bean으로 등록
// https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{

  // HTTPSecurity 설정
  @Bean
  @Order(SecurityProperties.BASIC_AUTH_ORDER)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
//        // cors enable 설정
        .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
//        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/login/**").permitAll()
//                .anyRequest().authenticated())
//        .httpBasic(Customizer.withDefaults())
//        .sessionManagement(httpSecuritySessionManagementConfigurer ->
//            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
//                SessionCreationPolicy.STATELESS))
          ;

    //    http
//        .authorizeHttpRequests((authz) -> authz
//            .anyRequest().authenticated()
//        )
//        .httpBasic(Customizer.withDefaults());

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      http
//          .authorizeHttpRequests((authz) -> authz
//              .anyRequest().authenticated()
//          )
//          .httpBasic(withDefaults());
//      return http.build();
//    }


//    http
//        .csrf().disable()
//        .headers().frameOptions().disable()
//        .and()
//        .authorizeRequests()
//        .anyRequest().authenticated()
//        .and()
//        .logout()
//        .logoutSuccessUrl("/")
//        .and()
//        .oauth2Login()
//        .userInfoEndpoint()
//        .userService(securityConfig);

    return http.build();
  }

  // WebSecurity 설정
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    // antMatchers 부분도 deprecated 되어 requestMatchers 대체
    return (web) -> web.ignoring().requestMatchers(
        "ignore1"
        , "/ignore2"
        , "/css/**"
        , "/js/**"
        , "/img/**"
        , "/lib/**"
        , "/favicon.ico"
    );
  }

  public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager
        .createUser(User.withUsername("user")
            .password(bCryptPasswordEncoder.encode("userpass"))
            .roles("USER")
            .build());
        manager.createUser(User.withUsername("admin")
            .password(bCryptPasswordEncoder.encode("adminpass"))
            .roles("USER","ADMIN")
            .build());
    return manager;
  }

  //  CORS 설정
  CorsConfigurationSource corsConfigurationSource() {
    return request -> {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedHeaders(Collections.singletonList("*"));
      config.setAllowedMethods(Collections.singletonList("*"));
      config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); // ⭐️ 허용할 origin
      config.setAllowCredentials(true);
      return config;
    };
  }

}
