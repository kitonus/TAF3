package com.indivaragroup.event_manager.event_manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Value("${security.enable-csrf:false}")
    private boolean csrfEnabled;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("admin").password("password").roles("ADMIN")
          .and().withUser("user").password("password").roles("USER");

    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/actuator/health").permitAll()
          .antMatchers("/actuator/**").hasRole("ADMIN")
          .antMatchers("/login").permitAll()
          .antMatchers("/error/**").permitAll()
          .antMatchers("/index.html").permitAll()
          .antMatchers("/**/v2/api-docs").permitAll()
			.antMatchers("/**/springfox-swagger-ui/**").permitAll()
			.antMatchers("/**/swagger-resources/**").permitAll()
			.antMatchers("/**/swagger*").permitAll()
		  .anyRequest().authenticated().
          and().formLogin().successForwardUrl("/training/participant/all").and().httpBasic();
        
      if(!csrfEnabled){
        http.csrf().disable();
      }          
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
