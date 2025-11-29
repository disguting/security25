package furman.security25.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests( req ->
                        req.requestMatchers("/index.html").permitAll()
                                .requestMatchers("/api/v1/cats/admin").hasRole("ADMIN")
                                .requestMatchers("/api/v1/cats/user").hasAnyRole("USER","ADMIN")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode("superadmin"))
                .roles("SUPERADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }


}