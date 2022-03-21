package com.example.wildfly.springboot.wildflyspringbootdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
              .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/echo/**")
                  .hasAuthority("User.Read")
                .antMatchers(HttpMethod.POST, "/echo/**")
                  .hasAuthority("User.Write")
                .anyRequest()
                  .authenticated()
            .and()
              .oauth2ResourceServer()
                .jwt();
    }

}
