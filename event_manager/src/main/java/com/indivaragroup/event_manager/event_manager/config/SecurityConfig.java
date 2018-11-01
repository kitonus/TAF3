package com.indivaragroup.event_manager.event_manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("admin").password("password").roles("ADMIN")
          .and().withUser("user").password("password").roles("USER");
          
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.httpBasic().and()
          .authorizeRequests()
          .antMatchers("/").hasRole("ADMIN")
          .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
    }
    
}
