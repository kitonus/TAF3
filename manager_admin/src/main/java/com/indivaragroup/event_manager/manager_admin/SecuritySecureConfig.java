package com.indivaragroup.event_manager.manager_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

//import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
@EnableWebSecurity
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
	private final Logger log = LoggerFactory.getLogger(SecuritySecureConfig.class);
	
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("admin").password("password").roles("ADMIN");       
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
    	log.info("==> adminContextPath is: "+adminContextPath);
    	
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath+"/");

        http.formLogin().successHandler(successHandler)
        .and()
        .logout()
//        .logoutUrl(adminContextPath+"/logout")
//        	.addLogoutHandler(new LogoutHandler() {
//			
//			@Override
//			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//				request.getSession().invalidate();
//				log.info("==>>Logged out!!!");
//				request.getRequestDispatcher(adminContextPath+"/login");
//			}
//		})
        .and()
//        .logout().logoutUrl(adminContextPath + "/logout").logoutSuccessUrl(adminContextPath + "/login").and()
        .httpBasic().and() 
        .authorizeRequests()
	        .antMatchers(adminContextPath + "/logout").permitAll()
        .antMatchers(adminContextPath + "/assets/**").permitAll() 
        .antMatchers(adminContextPath + "/error/**").permitAll()
        .antMatchers(adminContextPath + "/login").permitAll()
//        .antMatchers(adminContextPath + "/instances").permitAll()
        .anyRequest().authenticated() 
        .and().csrf().disable();
//        .csrf()
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  
//            .ignoringAntMatchers(
//                adminContextPath + "/instances",   
//                adminContextPath + "/actuator/**"  
//            );
        // @formatter:on
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