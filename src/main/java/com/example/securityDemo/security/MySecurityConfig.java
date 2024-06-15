package com.example.securityDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig
{
    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((request)->request
                .requestMatchers("/page1/", "/page2/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/marketer/**").hasRole("MARKETER")
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .anyRequest()
                .authenticated()
        ).formLogin((login)->login
                .loginPage("/customLogin/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard/", true)
                .permitAll()
        ).logout((logout)->logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/customLogin/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
        ).rememberMe((remember)->remember
                .rememberMeParameter("remember_me")
                .key("mysecretekey")
                .tokenValiditySeconds(60 * 60 * 60 * 24 * 7)
        );
        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder()
    {
    //    return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserDetailsService getUserDetailService()
    {
        List<UserDetails> listUser = new ArrayList<>();

        UserDetails admin = User.builder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build();

        UserDetails marketer = User.builder()
                .username("marketer")
                .password("123")
                .roles("MARKETER")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password("123")
                .roles("CUSTOMER")
                .build();

        listUser.add(admin);
        listUser.add(marketer);
        listUser.add(customer);

        return new InMemoryUserDetailsManager(listUser);
    }
}
