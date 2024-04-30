package sampleTest.demo.Config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import sampleTest.demo.Security.JwtAuthenticationEntryPoint;
import sampleTest.demo.Security.JwtAuthenticationFilter;

import java.io.IOException;

@Component
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).cors(cors->cors.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/home/**").
                        authenticated().requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll().anyRequest().authenticated()).   //
                exceptionHandling(ex->ex.authenticationEntryPoint(point)).
                sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider(){
       DaoAuthenticationProvider provider   = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
      return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}

