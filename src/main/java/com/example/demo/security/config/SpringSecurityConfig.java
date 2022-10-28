package com.example.demo.security.config;

import com.example.demo.security.CustomAuthenticationProvider;
import com.example.demo.security.CustomFailureHandler;
import com.example.demo.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    basic
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //     custom
//    @Autowired
//    private CustomAuthenticationProvider authProvider;
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
//        return authenticationManagerBuilder.build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
//                .antMatchers("/sign-in", "/sign-up").permitAll()
                .antMatchers("/", "/index", "/test").hasRole("ADMIN")
                .antMatchers("/static/**").permitAll()
                .anyRequest().permitAll();
//                .antMatchers().authenticated()
        httpSecurity
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pwd")
                .loginPage("/sign-in")
                .loginProcessingUrl("/auth/sign-in")
//                .defaultSuccessUrl("/index", true)
//                .failureUrl("/sign-in")
                .successHandler(new CustomSuccessHandler())
                .failureHandler(new CustomFailureHandler());
        httpSecurity
                .logout()
                .logoutUrl("/sign-out")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/sign-in");
        httpSecurity
                .exceptionHandling().accessDeniedPage("/sign-in");

//                .successHandler()

        return httpSecurity.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/static/**");
//    }


}
