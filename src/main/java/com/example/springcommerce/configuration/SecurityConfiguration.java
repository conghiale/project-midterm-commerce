package com.example.springcommerce.configuration;


import com.example.springcommerce.service.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Transactional
public class SecurityConfiguration {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/templates/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/img/**").permitAll()
                .requestMatchers("/xml/**").permitAll();

        //The pages does not require login
        http.authorizeHttpRequests()
                .requestMatchers("/login","/register","/logout")
                .permitAll();

        // For both of user, admin
        http.authorizeHttpRequests()
                .requestMatchers("/home")
                .hasAnyRole("ADMIN", "CUSTOMER");


         // For customer only
        http.authorizeHttpRequests()
                .requestMatchers("/cart","/addtocard/{id}","/deletetocard/{id}")
                .hasRole("CUSTOMER");

        http.authorizeHttpRequests()
                .and().rememberMe()
                .alwaysRemember(true)
                .tokenValiditySeconds(24 * 60 * 60) //24 hours
                .rememberMeCookieName("customerId")
                .key("somesecret");

        // Config for Login Form
        http.authorizeHttpRequests()
                .and().formLogin()// Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/home",true)
                .usernameParameter("username")
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");


        return http.build();
    }
}
