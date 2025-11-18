package furman.security25.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

/*
@author   User
@project   security25
@class  SecurityConfig
@version  1.0.0
@since 18.11.2025 - 12.22
*/
@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/archers/**").authenticated()
                        .anyRequest().permitAll()
                )


                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}