package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()
                
                .requestMatchers(HttpMethod.GET, "/students/**")
                .hasAuthority("STUDENT_READ")

            .requestMatchers(HttpMethod.POST, "/students/**")
                .hasAuthority("STUDENT_CREATE")

            .requestMatchers(HttpMethod.PUT, "/students/**")
                .hasAuthority("STUDENT_UPDATE")

            .requestMatchers(HttpMethod.DELETE, "/students/**")
                .hasAuthority("STUDENT_DELETE")

            .anyRequest().authenticated()
    )
            .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .authorities (
                		"STUDENT_READ",
                        "STUDENT_CREATE",
                        "STUDENT_UPDATE",
                        "STUDENT_DELETE"
)

                .build();
        
        


        		
        		
        
        

        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .authorities("STUDENT_READ")

                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
      
      
    		  
    		  
    }
