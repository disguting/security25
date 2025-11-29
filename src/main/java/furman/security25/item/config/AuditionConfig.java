package furman.security25.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/*
@author   User
@project   security25
@class  AuditionConfig
@version  1.0.0
@since 29.11.2025 - 20.18
*/
@EnableMongoAuditing
@Configuration
public class AuditionConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
