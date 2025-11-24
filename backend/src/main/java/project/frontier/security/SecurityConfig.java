package project.frontier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.frontier.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;
    
    @Bean
    public FireBaseAuthenticationFilter fireBaseAuthenticationFilter() {
        return new FireBaseAuthenticationFilter(userRepository);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                // public  endpoints
                                .requestMatchers("/health").permitAll()
                                
                                // profile endpoint
                                .requestMatchers("/auth/profile").authenticated()
                                
                                // admin APIs
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                
                                // colonist APIs
                                .requestMatchers("/colonist/**").hasAnyRole("COLONIST", "ADMIN")

                                // everything else requires login
                                .anyRequest().authenticated()
                )
                .addFilterBefore(fireBaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
