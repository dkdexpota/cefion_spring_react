package com.web_app.cefion.config;

import com.web_app.cefion.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConfigurer jwtConfigurer;

    public WebSecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/api/auth/registration").permitAll()
                .antMatchers("/auth/registration").permitAll()
                .antMatchers("/api/landing/road_map").permitAll()
                .antMatchers("/api/landing/media").permitAll()
                .antMatchers("/api/landing/media/img/**").permitAll()
                .antMatchers("/api/landing/numbers").permitAll()
                .antMatchers("/api/landing/faqs").permitAll()
                .antMatchers("/api/landing/faqs/problem/**").permitAll()
                .antMatchers("/api/landing/about").permitAll()
                .antMatchers("/api/landing/about/person/img/**").permitAll()
                .antMatchers("/api/news").permitAll()
                .antMatchers("/api/news/img/**").permitAll()
                .antMatchers("/api/news/**/page/**").permitAll()
                .antMatchers("/api/news/**/").permitAll()
                .antMatchers("/api/reg/create").permitAll()
                .antMatchers("/api/price").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}