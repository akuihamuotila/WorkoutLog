package fi.haagahelia.workoutlog.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService uds) {
        // Constructor for WebSecurityConfig
        this.userDetailsService = uds;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Configures the security filter chain
        // This method is called by Spring Security to configure the security settings
        http
            .userDetailsService(userDetailsService);
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
                .invalidateHttpSession(true)
            );

        return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Bean for password encoding
        return new BCryptPasswordEncoder();
    }
}