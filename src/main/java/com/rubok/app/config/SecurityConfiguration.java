package com.rubok.app.config;

import com.rubok.app.web.authenticate.AuthenticationFilter;
import com.rubok.app.web.authenticate.SimpleAuthenticationFailureHandler;
import com.rubok.app.web.authenticate.SimpleAuthenticationSuccessHandler;
import com.rubok.app.web.authenticate.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfiguration {

  private final AuthenticationConfiguration authenticationConfiguration;

  public SecurityConfiguration(AuthenticationConfiguration authenticationConfiguration) {
    this.authenticationConfiguration = authenticationConfiguration;
  }

  private static final String[] PUBLIC = new String[]{
    "/error", "/login", "/logout", "/register"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(PUBLIC).permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
      .formLogin()
      .loginPage("/login")
      .and()
      .logout()
      .logoutUrl("/logout")
      .logoutSuccessUrl("/login?logged-out")
      .and()
      .httpBasic(Customizer.withDefaults())
      .csrf().disable();
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().antMatchers("/static/**", "/js/**", "/ts/**", "/css/**", "/images/**", "/favicon.ico");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationFilter authenticationFilter() throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    authenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationFilter;
  }
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SimpleAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new SimpleAuthenticationFailureHandler();
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SimpleLogoutSuccessHandler();
  }
}
