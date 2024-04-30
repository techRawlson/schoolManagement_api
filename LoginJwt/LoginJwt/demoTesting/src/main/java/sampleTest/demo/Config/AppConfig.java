package sampleTest.demo.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.builder().username("aman").password(passwordEncoder().encode("abc")).roles("admin").build();
//        UserDetails user2= User.builder().username("nandini").password(passwordEncoder().encode("abc")).roles("admin").build();
//        return new InMemoryUserDetailsManager(user,user2);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
